s.boot;

b = Buffer.read(s, "/home/kelcey/audio/meowmono.wav");
c = Buffer.read(s ,"/home/kelcey/audio/eddiemairmono.wav");

{PlayBuf.ar(1, b)}.play

(
SynthDef(\creepycat, {
	var a, out;
	a = LFNoise2.kr(1).range(0, 1);
	e = LFNoise2.kr(1).range(-1,1);
	out = GrainBuf.ar(2, Dust.kr(700), 0.1, b, 0.1, a, 2, e, -1, 4096, 0.2);
	out = out +  GrainBuf.ar(2, Dust.kr(700), 0.1, b, 0.5, a, 2, e, -1, 4096, 0.2);
	Out.ar(0, out);
	}
).add;
)

x = Synth(\creepycat);