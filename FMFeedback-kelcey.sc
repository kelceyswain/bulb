s.boot;
(
~options = Server.local.options;
~options.memSize;
~options.memSize_(2**17);
)
s.quit;

s.options.memSize

(
GeneralHID.buildDeviceList;
d = GeneralHID.deviceList;
GeneralHID.postDevices;
a = GeneralHID.open( d[10] )
/*a.debug_( false );
a.makeGui;*/
)

(
SynthDef(\sky,
	{ | p1, p2, d1, d2, e1, e2 |
		var local;
		var synth;
		var mod;
		var maxDelay;
		var x1, y1;
		local = LocalIn.ar(16);

		// Starting frequency
		// p = Array.fill(16, { LinExp.kr(LFNoise0.kr(Rand(0,0.1)), -1, 1, 0.25, 1500)});
		p = [p1*400+220, p2*400+220];
		//p = [440,440,660,660,440,440,660,660,440,440,660,660,440,440,660,660];
		// Comb delay array
		// d = Array.fill(16, { LFNoise0.kr(Rand(0,0.1)).range(0.0004, 0.04) });
		d = [d1*0.1, d2*0.1];
		// Comb decay array
		c = Array.fill(2, { LFNoise0.kr(Rand(0,0.1)).range(0.01, 1) });
		// Feedback delay array
		e = Array.fill(2, { LinExp.kr(LFNoise2.kr(Rand(0,1)), -1, 1, 0.001, 0.5)});

		maxDelay = 1.0;
		x1 = DelayL.ar(local[0], maxDelay, e[0]);
		y1 = DelayL.ar(local[1], maxDelay, e[1]);

		mod = 10;
		synth = [
			Pulse.ar(p[0]+(y1*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, (y1*0.5+0.5)*e1),
			Pulse.ar(p[1]+(x1*mod* LFNoise1.kr(5.reciprocal).abs), 0.5, (x1*0.5+0.5)*e2),
		];


		synth = CombN.ar(synth, 0.1, d, c, 20.reciprocal);
		synth = Limiter.ar(synth, 0.95);
		LocalOut.ar(Normalizer.ar(synth, 0.95, 0.01));

		//synth = Limiter.ar(synth, 0.5);
		// OffsetOut.ar(0, Mix.fill(2, GVerb.ar(synth, 30)));
		OffsetOut.ar(0, synth
		);
	}
).add;
)

s.scope;

(
a.slots[3].at( 0 ).createBus( s );
a.slots[3].at( 1 ).createBus( s );
a.slots[3].at( 3 ).createBus( s );
a.slots[3].at( 4 ).createBus( s );
a.slots[3].at( 2 ).createBus( s );
a.slots[3].at( 5 ).createBus( s );
x = Synth.new(\sky);
x.map(\d1, a.slots[3].at(0).bus);
x.map(\d2, a.slots[3].at(1).bus);
x.map(\p1, a.slots[3].at(3).bus);
x.map(\p2, a.slots[3].at(4).bus);
x.map(\e1, a.slots[3].at(2).bus);
x.map(\e2, a.slots[3].at(5).bus);
)
