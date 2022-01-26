/****************************************************************/
/*                                                              */
/*          Advanced Navigation Packet Protocol Library         */
/*          .NET C# Language Orientus SDK, Version 1.1          */
/*   Copyright 2013, Xavier Orr, Advanced Navigation Pty Ltd    */
/*                                                              */
/****************************************************************/
/*
 * Copyright (C) 2013 Advanced Navigation Pty Ltd
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ANPPExample
{
    public partial class Form1 : Form
    {
        Boolean serialConnected = false;
        ANPacketDecoder anPacketDecoder;

        public Form1()
        {
            InitializeComponent();
            anPacketDecoder = new ANPacketDecoder();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            comboBaud.SelectedIndex = 7;
            updatePorts();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            updatePorts();
        }

        private void updatePorts()
        {
            if (!serialConnected)
            {
                string[] ports = System.IO.Ports.SerialPort.GetPortNames();
                if (comboPort.Items.Count != ports.Length)
                {
                    comboPort.Items.Clear();
                    foreach (string portName in ports)
                    {
                        comboPort.Items.Add((object)portName);
                    }
                    comboPort.SelectedIndex = 0;
                }
            }
        }

        private void buttonConnect_Click(object sender, EventArgs e)
        {
            if (serialConnected)
            {
                try
                {
                    serialPort1.Close();
                    buttonConnect.Text = "Connect";
                    serialConnected = false;
                }
                catch { }
            }
            else
            {
                try
                {
                    serialPort1.PortName = comboPort.Text;
                    serialPort1.BaudRate = Convert.ToInt32(comboBaud.Text);
                    serialPort1.Open();
                    buttonConnect.Text = "Disconnect";
                    serialConnected = true;
                }
                catch { }
            }
        }

        private void serialPort1_DataReceived(object sender, System.IO.Ports.SerialDataReceivedEventArgs e)
        {
            try
            {
                anPacketDecoder.bufferLength += serialPort1.Read(anPacketDecoder.buffer, anPacketDecoder.bufferLength, anPacketDecoder.buffer.Length - anPacketDecoder.bufferLength);
                ANPacket packet = null;
                while ((packet = anPacketDecoder.packetDecode()) != null)
                {
                    switch (packet.id)
                    {
                        case ANPacket.PACKET_ID_STATUS:
                            if (packet.length == 4)
                            {
                                ANPacket23 anPacket23 = new ANPacket23(packet);
                                this.Invoke((MethodInvoker)delegate
                                {
                                    richTextBox1.AppendText("Received Status Packet\n");
                                    checkOrientationReady.Checked = anPacket23.orientationInitialised;
                                    checkHeadingReady.Checked = anPacket23.headingInitialised;
                                    checkStatusAlert.Checked = anPacket23.statusAlert;
                                });
                            }
                            break;
                        case ANPacket.PACKET_ID_RAW_SENSORS:
                            if (packet.length == 48)
                            {
                                ANPacket28 anPacket28 = new ANPacket28(packet);
                                this.Invoke((MethodInvoker)delegate
                                {
                                    richTextBox1.AppendText("Received Raw Sensors Packet\n");
                                });
                            }
                            break;
                        case ANPacket.PACKET_ID_EULER_ORIENTATION:
                            if (packet.length == 12)
                            {
                                ANPacket39 anPacket39 = new ANPacket39(packet);
                                this.Invoke((MethodInvoker)delegate
                                {
                                    richTextBox1.AppendText("Received Euler Orientation Packet\n");
                                    textBoxRoll.Text = (anPacket39.orientation[0] * 180 / Math.PI).ToString();
                                    textBoxPitch.Text = (anPacket39.orientation[1] * 180 / Math.PI).ToString();
                                    textBoxYaw.Text = (anPacket39.orientation[2] * 180 / Math.PI).ToString();
                                });
                            }
                            break;
                    }
                }
            }
            catch { }
        }
    }
}
