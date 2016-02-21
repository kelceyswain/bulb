/*
First attempts at controlling a synth with TouchOSC.

Notes:
 - A GUI would be good; I'm terrible at making them so I haven't bothered.
 - I'm using the 'Simple' layout in the TouchOSC application to make it
   easy to get started.
 - The controls aren't optimised (for example, only the left-most of the four
   toggle buttons has functionality), but it should still be useful as a
   first example.

How to use:
 - The toggle button on the bottom left controls the envelope gate - basically
   it turns the sound on and off.
 - The horizontal fader controls panning.
 - the 1st fader controls volume; the 2nd controls frequency; the 3rd controls
   the frequency of the Saw wave that acts as the kernel in the convolved sound;
   the 4th controls the width ratio of the Pulse wave.
*/

s.boot;

// Define the synth and the OSC controls:
(
SynthDef(\touchOSC,
	{
		arg freq = 440, width = 0.5, pan = 0, mul = 0, gate = 0, kernFreq = 40;
		var env;
		var kern;
		var sig;

		env = Env.asr(0.6, 0.8, 1.2, -3);
		env = EnvGen.ar(env, gate, doneAction: 0);

		kern = Saw.ar(kernFreq, 0.2);

		sig = Pulse.ar([freq, freq+2], width, 0.2);
		sig = Convolution.ar(sig, kern, mul: env);
		sig = Balance2.ar(sig[0], sig[1], pan, mul*0.5);

		Out.ar(0, sig);
	}
).add;

OSCdef.new(
	\toggle1,
	{
		arg msg;
		x.set(\gate, msg[1]);
	},
	'/1/toggle1';
);

OSCdef.new(
	\fader1,
	{
		arg msg;
		x.set(\mul, msg[1]);
	},
	'/1/fader1';
);

OSCdef.new(
	\fader2,
	{
		arg msg;
		x.set(\freq, msg[1].linexp(0,1,200,3200));
	},
	'/1/fader2';
);

OSCdef.new(
	\fader3,
	{
		arg msg;
		x.set(\kernFreq, msg[1].linexp(0,1,10,100));
	},
	'/1/fader3';
);

OSCdef.new(
	\fader4,
	{
		arg msg;
		x.set(\width, msg[1].linlin(0,1,0.5,0.9));
	},
	'/1/fader4';
);

OSCdef.new(
	\fader5,
	{
		arg msg;
		x.set(\pan, msg[1].linlin(0,1,-1,1));
	},
	'/1/fader5';
);
)

// Now run the synth:
x = Synth(\touchOSC);
x.free;

s.quit;