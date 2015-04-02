package antgame;

public abstract class Instruction{}

public class Sense extends Intsruction{
	SenseDirection sensedir;
	int st1, st2;
	Condition cond;
}

public class Mark extends Intsruction{
	int i, st;
}

public class PickUp extends Intsruction{
	int st1, st2;
}

public class Drop extends Intsruction{
	int st;
}

public class Turn extends Intsruction{
	LeftOrRight lr;
	int st;
}

public class Move extends Intsruction{
	int st1, st2;
}

public class Flip extends Intsruction{
	int p, st1, st2;
}
