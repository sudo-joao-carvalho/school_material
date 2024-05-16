// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.io.InvalidObjectException;
import java.util.HashSet;
import java.util.Set;

/*class ObjectBase {
    protected double d;
    public ObjectBase(double d) { this.d = d; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ObjectBase other)
            return d == other.d;
        return false;
    }
    @Override
    public int hashCode() { return 0; }
}

class Object1 extends ObjectBase{
    static int count = 0;
    public Object1(double d) { super(d); }
    @Override
    public int hashCode() {
        return count++ / 2;
    }
}

class Object2 extends ObjectBase {
    public Object2(double d) { super(d); }
}

class Object3 extends ObjectBase {
    static int count = 1;
    public Object3(double d) {
        super(d);
    }
    @Override
    public int hashCode() {
        return count++ % 2;
    }
}

public class Main {
    public static void main(
            String[] args) {
        Set<Object> set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            System.out.println(
                    set.add(new Object1(1.0)));
            System.out.println(
                    set.add(new Object2(2.0)));
            System.out.println(
                    set.add(new Object3(3.0)));
        }
        System.out.println(set.size());
    }
}*/

/*class MyException extends InvalidObjectException {
    public MyException(String reason) {
        super(reason);
    }
    @Override
    public String toString() {
        return "Error: "+super.toString();
    }
}
class Project {
    String name;
    String teacher;
    String student;
    public Project(String name, String teacher, String student) throws Exception {
        this.name = name;
        this.teacher = teacher;
        this.student = student;
        if (teacher.equals(student))
            throw new MyException("Teacher and Student have the same name");
    }
    @Override
    public String toString() {
        return String.format("%s / %s / %s",name,teacher,student);
    }
}
public class Main {
    public static Project createProject(String name, String teacher, String student) {
        try {
            return new Project(name, teacher, student);
        } catch (MyException e) {
            System.out.println("Msg A");
        } catch (Exception e) {
            System.out.println("Msg B");
        } finally {
            System.out.println("Msg C");
        }
        System.out.println("Msg D");
        return null;
    }
    public static void main(String[] args) {

        System.out.println("Begin");
        Project a = createProject("A","B","C");
        Project b = createProject("A","B","B");
        Project c = createProject("A",null,"C");
        System.out.println(a+"\n"+b+"\n"+c);
        System.out.println("End");
    }
}*/

public class Main {

    public static void main(String[] args){

    }
}
