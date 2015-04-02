package antgame;

public abstract class Instruction{}

public class Sense extends Instruction{
	SenseDirection sensedir;
	int st1, st2;
	Condition cond;
}

public class Mark extends Instruction{
	int i, st;
}

public class PickUp extends Instruction{
	int st1, st2;
}

public class Drop extends Instruction{
	int st;
}

public class Turn extends Instruction{
	LeftOrRight lr;
	int st;
}

public class Move extends Instruction{
	int st1, st2;
}

public class Flip extends Instruction{
	int p, st1, st2;
}
