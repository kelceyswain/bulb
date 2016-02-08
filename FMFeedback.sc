s.boot;
(
~options = Server.local.options;
~options.memSize;
~options.memSize_(2**17);
)
s.quit;

s.options.memSize

(
SynthDef("sky",
	{
		arg decay = 0.2, delay = 0.01;
		var local;
		var synth;
		var mod;
		var maxDelay;
		var x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8;
		local = LocalIn.ar(16);

		// What happens if it is arrays of 8, stereo pairs?
		// Starting frequency
		p = Array.fill(16, { LinExp.kr(LFNoise0.kr(Rand(0,0.1)), -1, 1, 0.25, 1500)});
		//p = [440,440,660,660,440,440,660,660,440,440,660,660,440,440,660,660];
		// Comb delay array
		d = Array.fill(16, { LFNoise0.kr(Rand(0,0.1)).range(0.0004, 0.04) });
		// Comb decay array
		c = Array.fill(16, { LFNoise0.kr(Rand(0,0.1)).range(0.01, 1) });
		// Feedback delay array
		e = Array.fill(16, { LinExp.kr(LFNoise2.kr(Rand(0,1)), -1, 1, 0.001, 0.5)});

		maxDelay = 1.0;
		x1 = DelayL.ar(local[0], maxDelay, e[0]);
		y1 = DelayL.ar(local[1], maxDelay, e[1]);
		x2 = DelayL.ar(local[2], maxDelay, e[2]);
		y2 = DelayL.ar(local[3], maxDelay, e[3]);
		x3 = DelayL.ar(local[4], maxDelay, e[4]);
		y3 = DelayL.ar(local[5], maxDelay, e[5]);
		x4 = DelayL.ar(local[6], maxDelay, e[6]);
		y4 = DelayL.ar(local[7], maxDelay, e[7]);
		x5 = DelayL.ar(local[8], maxDelay, e[8]);
		y5 = DelayL.ar(local[9], maxDelay, e[9]);
		x6 = DelayL.ar(local[10], maxDelay, e[10]);
		y6 = DelayL.ar(local[11], maxDelay, e[11]);
		x7 = DelayL.ar(local[12], maxDelay, e[12]);
		y7 = DelayL.ar(local[13], maxDelay, e[13]);
		x8 = DelayL.ar(local[14], maxDelay, e[14]);
		y8 = DelayL.ar(local[15], maxDelay, e[15]);

		mod = 10;
		synth = [
			Pulse.ar(p[0]+(y1*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, y1*0.5+0.5),
			Pulse.ar(p[1]+(x1*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, x1*0.5+0.5),
			Pulse.ar(p[2]+(y2*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, y2*0.5+0.5),
			Pulse.ar(p[3]+(x2*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, x2*0.5+0.5),
			Pulse.ar(p[4]+(y3*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, y3*0.5+0.5),
			Pulse.ar(p[5]+(x3*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, x3*0.5+0.5),
			Pulse.ar(p[6]+(y4*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, y4*0.5+0.5),
			Pulse.ar(p[7]+(x4*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, x4*0.5+0.5),
			Pulse.ar(p[8]+(y5*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, y5*0.5+0.5),
			Pulse.ar(p[9]+(x5*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, x5*0.5+0.5),
			Pulse.ar(p[10]+(y6*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, y6*0.5+0.5),
			Pulse.ar(p[11]+(x6*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, x6*0.5+0.5),
			Pulse.ar(p[12]+(y7*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, y7*0.5+0.5),
			Pulse.ar(p[13]+(x7*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, x7*0.5+0.5),
			Pulse.ar(p[14]+(y8*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, y8*0.5+0.5),
			Pulse.ar(p[15]+(x8*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, x8*0.5+0.5)
		];


		synth = CombN.ar(synth, 0.1, d, c, 20.reciprocal);
		synth = Limiter.ar(synth, 0.95);
		LocalOut.ar(Normalizer.ar(synth, 0.95, 0.01));

		//synth = Limiter.ar(synth, 0.5);
		// OffsetOut.ar(0, Mix.fill(2, GVerb.ar(synth, 30)));
		OffsetOut.ar(0, [
			Mix.new([
				synth[0], synth[2], synth[4], synth[6], synth[8], synth[10], synth[12], synth[14]
			]),
			Mix.new([
				synth[1], synth[3], synth[5], synth[7], synth[9], synth[11], synth[13], synth[15]
			])
			]
		);
	}
).add;
)

s.scope;
a = Synth("sky");
