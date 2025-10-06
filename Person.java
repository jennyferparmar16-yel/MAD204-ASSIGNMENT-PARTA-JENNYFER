/**
  Person.java
  Description:
  - Base class that represents a general person in the system.
  - Stores ID, name, and age, and provides methods to introduce and celebrate a birthday.
  - This class is inherited by Student, Professor, and TeachingAssistant.
  - Jennyfer Parmar
 * Date: 06/10/2025
 */
public abstract class Person
{
    //protected attributess accessible to subclasses
    private int id;
    protected String name;
    private  int age;

    //constructor
    public  Person(int id, String name, int age)
    {
        this.id=id;
        this.name=name;
        this.age=age;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }

    // celebrateBirthday Method
    public void celebrateBirthday()
    {
        age=age+1;
        System.out.println(name+" is now"+ age+"Years old.");
    }
    //abstract method for subclasses
    public abstract String introduce();

    //toString method for displaying info.
    @Override
    public String toString(){
        return "ID:" +id+ "Name:"+name+"AGE"+age;
    }
}
