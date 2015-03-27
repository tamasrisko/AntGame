public class Condition{}

public class Friend extends Condition{}
public class Foe extends Condition{}
public class FriendWithFood extends Condition{}
public class FoeWithFood extends Condition{}
public class Food extends Condition{}
public class Rock extends Condition{}
public class Marker extends Condition{
	int marker;
	public Marker(int marker){
		this.marker = marker;
	}
}
public class FoeMarker extends Condition{
}
public class Home extends Condition{}
public class FoeHome extends Condition{}
