s.boot;

GeneralHID.buildDeviceList;
d = GeneralHID.deviceList;
GeneralHID.postDevices;
a = GeneralHID.open( d[10] )
a.debug_( false );
a.makeGui;

(
SynthDef( \xbox, {| out = 0, n1 = 0.001, n2 = 0.001, p1 = 10, p2 = 10|
	Out.ar( out,
		LPF.ar(
			CombC.ar(
				WhiteNoise.ar(), 0.1, [n1*0.01+0.0004, n2*0.01+0.0004], 0.1, 0.2)
			, 500, Pulse.kr([p1*20, p2*20]).range(0,1))
	);
}).add;
)
(
a.slots[3].at( 0 ).createBus( s );
a.slots[3].at( 1 ).createBus( s );
a.slots[3].at( 3 ).createBus( s );
a.slots[3].at( 4 ).createBus( s );
x = Synth.new(\xbox);
x.map(\n1, a.slots[3].at(0).bus);
x.map(\n2, a.slots[3].at(1).bus);
x.map(\p1, a.slots[3].at(3).bus);
x.map(\p2, a.slots[3].at(4).bus);
)


