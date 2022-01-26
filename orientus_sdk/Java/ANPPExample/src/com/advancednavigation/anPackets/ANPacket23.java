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

public class ANPacket23
{
	public Boolean statusAlert = false;
	
	public Boolean systemFailure = false;
	public Boolean accelerometerSensorFailure = false;
	public Boolean gyroscopeSensorFailure = false;
	public Boolean magnetometerSensorFailure = false;
	public Boolean accelerometerOverRange = false;
	public Boolean gyroscopeOverRange = false;
	public Boolean magnetometerOverRange = false;
	public Boolean minimumTemperatureAlarm = false;
	public Boolean maximumTemperatureAlarm = false;
	public Boolean lowVoltageAlarm = false;
	public Boolean highVoltageAlarm = false;
	public Boolean dataOverflowAlarm = false;
	
	public Boolean orientationFilterInitialised = false;
	public Boolean headingInitialised = false;
	public Boolean magnetometersActive = false;
	public Boolean velocityHeadingActive = false;
	public Boolean externalPositionActive = false;
	public Boolean externalVelocityActive = false;
	public Boolean externalHeadingActive = false;
	
	public ANPacket23()
	{
	}

	public ANPacket23(ANPacket packet)
	{
		if (packet.data[0] != 0)
		{
			statusAlert = true;
			if ((packet.data[0] & 0x01) != 0) systemFailure = true;
			if ((packet.data[0] & 0x02) != 0) accelerometerSensorFailure = true;
			if ((packet.data[0] & 0x04) != 0) gyroscopeSensorFailure = true;
			if ((packet.data[0] & 0x08) != 0) magnetometerSensorFailure = true;
			if ((packet.data[0] & 0x40) != 0) accelerometerOverRange = true;
			if ((packet.data[0] & 0x80) != 0) gyroscopeOverRange = true;
		}
		if(packet.data[1] != 0)
		{
			statusAlert = true;
			if ((packet.data[1] & 0x01) != 0) magnetometerOverRange = true;
			if ((packet.data[1] & 0x04) != 0) minimumTemperatureAlarm = true;
			if ((packet.data[1] & 0x08) != 0) maximumTemperatureAlarm = true;
			if ((packet.data[1] & 0x10) != 0) lowVoltageAlarm = true;
			if ((packet.data[1] & 0x20) != 0) highVoltageAlarm = true;
			if ((packet.data[1] & 0x80) != 0) dataOverflowAlarm = true;
		}
		if((packet.data[2] & 0x01) != 0) orientationFilterInitialised = true;
		if((packet.data[2] & 0x04) != 0) headingInitialised = true;
		if((packet.data[3] & 0x04) != 0) magnetometersActive = true;
		if((packet.data[3] & 0x08) != 0) velocityHeadingActive = true;
		if((packet.data[3] & 0x20) != 0) externalPositionActive = true;
		if((packet.data[3] & 0x40) != 0) externalVelocityActive = true;
		if((packet.data[3] & 0x80) != 0) externalHeadingActive = true;
	}
	
	public ANPacket23(byte[] data, int offset)
	{
		if (data[offset] != 0)
		{
			statusAlert = true;
			if ((data[offset] & 0x01) != 0) systemFailure = true;
			if ((data[offset] & 0x02) != 0) accelerometerSensorFailure = true;
			if ((data[offset] & 0x04) != 0) gyroscopeSensorFailure = true;
			if ((data[offset] & 0x08) != 0) magnetometerSensorFailure = true;
			if ((data[offset] & 0x40) != 0) accelerometerOverRange = true;
			if ((data[offset] & 0x80) != 0) gyroscopeOverRange = true;
		}
		if(data[offset + 1] != 0)
		{
			statusAlert = true;
			if ((data[offset + 11] & 0x01) != 0) magnetometerOverRange = true;
			if ((data[offset + 1] & 0x04) != 0) minimumTemperatureAlarm = true;
			if ((data[offset + 1] & 0x08) != 0) maximumTemperatureAlarm = true;
			if ((data[offset + 1] & 0x10) != 0) lowVoltageAlarm = true;
			if ((data[offset + 1] & 0x20) != 0) highVoltageAlarm = true;
			if ((data[offset + 1] & 0x80) != 0) dataOverflowAlarm = true;
		}
		if((data[offset + 2] & 0x01) != 0) orientationFilterInitialised = true;
		if((data[offset + 2] & 0x04) != 0) headingInitialised = true;
		if((data[offset + 3] & 0x04) != 0) magnetometersActive = true;
		if((data[offset + 3] & 0x08) != 0) velocityHeadingActive = true;
		if((data[offset + 3] & 0x20) != 0) externalPositionActive = true;
		if((data[offset + 3] & 0x40) != 0) externalVelocityActive = true;
		if((data[offset + 3] & 0x80) != 0) externalHeadingActive = true;
	}
}
