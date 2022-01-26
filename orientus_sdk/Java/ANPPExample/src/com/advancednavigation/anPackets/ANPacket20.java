/****************************************************************/
/*                                                              */
/*         Advanced Navigation Packet Protocol Library          */
/*           Java Language Orientus SDK, Version 1.1            */
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

package com.advancednavigation.anPackets;

import java.util.Date;

public class ANPacket20
{
	public ANPacket23 status;

	public long seconds;
	public long microseconds;
	
	public float[] orientation;
	public float[] angularVelocity;
	
	public ANPacket20()
	{
		status = new ANPacket23();
		seconds = 0;
		microseconds = 0;
		
		orientation = new float[3];
		orientation[0] = 0;
		orientation[1] = 0;
		orientation[2] = 0;
		angularVelocity = new float[3];
		angularVelocity[0] = 0;
		angularVelocity[1] = 0;
		angularVelocity[2] = 0;
	}

	public ANPacket20(ANPacket packet)
	{
		status = new ANPacket23(packet.data,0);
		
		seconds = TypeConversion.bytesToUInt32(packet.data, 4);
		microseconds = TypeConversion.bytesToUInt32(packet.data, 8);
		
		orientation = new float[3];
		orientation[0] = TypeConversion.bytesToFloat(packet.data, 64);
		orientation[1] = TypeConversion.bytesToFloat(packet.data, 68);
		orientation[2] = TypeConversion.bytesToFloat(packet.data, 72);
		
		angularVelocity = new float[3];
		angularVelocity[0] = TypeConversion.bytesToFloat(packet.data, 76);
		angularVelocity[1] = TypeConversion.bytesToFloat(packet.data, 80);
		angularVelocity[2] = TypeConversion.bytesToFloat(packet.data, 84);
	}
}
