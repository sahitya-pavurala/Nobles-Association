import java.util.*;


/**
Program to model a game of medieval times where world is filled with warriors and nobles. 
In this program the nobles hire the warriors to battle against
another noble.The noble with less strength dies, and the noble with
more strength will have a decrease in strength. 
@author : Sahitya Pavurala
//ID : 0490373
//HW 03
*/

class Noble
{
	/** name is the member variable of Noble which is of type string which holds the name of the noble*/
	private String name;
	/** army is the member variable of Noble which is an arraylist that holds warriors */
	private List<Warrior> army = new ArrayList<Warrior>();   
	/** alive is the member variable of Noble which is a boolean value to show if the noble is alive or not*/
	private boolean alive = true;
	
	/** Noble(String name) is a single argument constructor which initialises the name	
	 @param		name				name of the Noble given as a parameter
	 */
	Noble(String name)
	{
		this.name = name; 
	}
	
	/** getNobleName() is a getter method for returning the noble's name of type String	
	 @return	name		it is the name of the noble 		    
	 */
	String getNobleName()
	{
		return name;
	}
	
	/** getArmy() is a getter method for returning the noble's army of type List<Warrior>	
	 @return	army		it is the army of the noble 		    
	 */
	List<Warrior> getArmy()
	{
		return army;
	}
	
	/** strength() is the method which returns the strength of the noble, which is the 
		combined strength of all thhe warriors in his army
	 @return	strength		it is the total strength of the noble		    
	 */
	double strength()
	{
		double strength = 0;
		// the for loop gets the indvidual strength of all the warriors in army to give the nobles strength
		for(Warrior warrior:army)
		{
			strength += warrior.getWarriorStrength();
		}
		return strength;
	}

	/** hire(Warrior soldier) is the method to be called when the noble wants to hire a warrior 
	 @param		soldier			it is the warrior which is to be added to the army of noble
	 */
	void hire(Warrior soldier)
	{	
		if (soldier.getMaster() == null && this.alive == true)
		{
			//add the warrior into the army og noble
			army.add(soldier);
			//calls the setMaster method of Warriior to set the warrior's master to this Noble
			soldier.setMaster(this);
		}
		else if(this.alive == false)
			{ System.out.println(this.name + " is already dead, so he cannot hire any warrior ");}
		else{ System.out.println(soldier.getWarriorName() +" is already hired!!! you cannot hire him " +this.name );}	
	}
	
	/** battle(Noble opponent) is called when a noble want to battle against anothe noble	
	 @param		opponent		it is the noble against whom th battles should happen
	 */
	void battle(Noble opponent)
	{	
		System.out.println(this.name + " battles " + opponent.name);
		//checks if both the nobles in the battle are already dead
		if	(opponent.alive == false && this.alive == false)
		{System.out.println("Oh, NO!  They're both dead!  Yuck! ");}
		//checks if the noble is dead and tells that he is dead
		else if(this.alive == false)
		{System.out.println("He's dead " + opponent.name);}
		//checks if opponent of the noble is dead and tells that he is dead
		else if (opponent.alive == false)
		{System.out.println("He's dead " + this.name);}
		//checks if the noble is noble has less strength than the opponent
		else if(this.strength() < opponent.strength())
		{	
			//calls the defeats method passing the noble as parameter
			opponent.defeats(this);
		}
		//checks if the noble is noble has more strength than the opponent
		else if(this.strength() > opponent.strength())
		{	
			//calls the defeats method passing the opponent as parameter		
			this.defeats(opponent);	
		}
		//checks the other condition that is if both the noble and opponent have the same strength
		else
		{	
			//calls the die method on both the noble and his opponent bcause they have the same strength
			this.die();
			opponent.die();
			System.out.println("Mutual Annihalation: " + this.name + " and " + opponent.name + " die at each other's hands ");
		}
	}
	
	
	/** die() is the method which is called when then noble with lesser strength
		in a battles loses against other noble 	
	 */
	void die()
	{	
		// makes the boolean member alive as false
		alive = false;
		//in this loop each of the warriror strengths in the army are decreased to zero
		for (Warrior warrior: army) 
		{
			warrior.decreaseStrength(1.0);
		}
	}
	
	/** defeats(Noble opponent) is the method which is called when the noble with greater strength
		in a battle wins against other noble
	 @param		opponent			the noble who gets defeated 
	 */
	void defeats(Noble opponent)
	{	
		//in this loop each of the warriror strengths in the army are decreased
		for (Warrior warrior :army) 
		{
			//warriror strength is decreased by calling the decreaseStrength(double) method of the warrior
			warrior.decreaseStrength(opponent.strength() / this.strength());
		}
		opponent.die();
		System.out.println(this.name + " defeats " + opponent.name );   
	}
	
	@Override //here we are overriding the toString method to display the nobles information
	public String toString()
	{
		String res = null;
		res = this.name + " has an army of " + army.size() + "\n " +  "\t";
		for(Warrior i : army)
		{
			res += i.getWarriorName() + " : " + i.getWarriorStrength() + "\n" +  "\t";
		}
		return res;
	}
}

class Warrior
{	
	/** strength is the member variable of Warrior which is of type double which holds the strength of the warrior*/
	private double strength;
	/** name is the member variable of Warrior which is of type String which holds the name of the warrior*/
	private String name;
	/** master is the member variable of Warrior which is of type Noble which holds the master of the warrior*/
	private Noble master;
	
	/** Warrior(String name,int strength) is a constructor which takes two arguments and initialises the name and strength	
	 @param		name				name of the warrior given as a parameter
	 @param		strength			strength of the warrior given as a parameter
	*/
	Warrior(String name,int strength)
	{
		this.name = name;
		this.strength = strength;
	}
	
	/** getWarriorName() is a getter method for returning the warrior's name of type String	
	 @return	name		it is the name of the warrior		    
	 */
	String getWarriorName()
	{
		return name;
	}
	
	/** getMaster() is a getter method for getting the nobles name under which 
		the warrior is hired 
	 @return		master		it is the Noble who hires the warrior		    
	*/
	Noble getMaster()
	{
		return master;
	}
	/** setMaster(Noble master) is a setter method for setting the nobles name under which 
		the warrior is hired 
	 @param		master		it is the Noble who hires the warrior		    
	*/	
	void setMaster(Noble master)
	{
		this.master = master;
	}
	
	/** getWarriorStrength() is a getter method for returning the warrior's strength of type Double
	 @return	strength		it is the strength of the warrior 		    
	*/
	double getWarriorStrength()
	{
		return strength;
	}
	
	/** runaway() is the method which is called when a warrior wants to quit his job under a noble 
	*/	
	void runaway()
	{	
		if(master != null)
		{
			System.out.println("So long " + master.getNobleName() + " I'm out'a here! -- " + this.name);
			master.getArmy().remove(this);
			master =  null ;
		}
		else
		{	System.out.println("How did you think you can quit " + this.name + ", when you dont even have a job!!!");	}
	}
	
	/** decreaseStrength(double ratio) is the method which is called when the nobles battle against each other and the strengths of 
		warriors should be decreased in a certain ratio
	 @param		ratio		it is the ratio based on which the warriors strength is to be decreased 
	*/
	void decreaseStrength(double ratio) 
	{
		strength *= 1-ratio;
	}
 
}
class WarriorsNobles {
    public static void  main(String[] args) {
        Noble art = new Noble("King Arthur");
        Noble lance = new Noble("Lancelot du Lac");
        Noble jim = new Noble("Jim");
        Noble linus = new Noble("Linus Torvalds");
        Noble billie = new Noble("Bill Gates");

        Warrior cheetah = new Warrior("Tarzan", 10);
        Warrior wizard = new Warrior("Merlin", 15);
        Warrior theGovernator = new Warrior("Conan", 12);
        Warrior nimoy = new Warrior("Spock", 15);
        Warrior lawless = new Warrior("Xena", 20);
        Warrior mrGreen = new Warrior("Hulk", 8);
        Warrior dylan = new Warrior("Hercules", 3);

        jim.hire(nimoy);
        lance.hire(theGovernator);
        art.hire(wizard);
        lance.hire(dylan);
        linus.hire(lawless);
        billie.hire(mrGreen);
        art.hire(cheetah);
		//art.hire(dylan);

        System.out.println(jim);
        System.out.println(lance);
        System.out.println(art);
        System.out.println(linus);
        System.out.println(billie);

        cheetah.runaway();
		//cheetah.runaway();
        System.out.println(art);

        art.battle(lance);
        jim.battle(lance);
        linus.battle(billie);
        billie.battle(lance);
		//art.hire(cheetah);
		System.out.println("========================");

        System.out.println(jim);
        System.out.println(lance);
        System.out.println(art);
        System.out.println(linus);
        System.out.println(billie);
    }
}
