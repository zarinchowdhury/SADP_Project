

import java.util.*;

public class DesignPatternsDemo {
    public static void main(String[] args) {
        System.out.println("=== Singleton (Logger) ===");
        Logger logger = Logger.getInstance();
        logger.log("Application started");

        System.out.println("\n=== Factory (AnimalFactory) ===");
        Animal dog = AnimalFactory.create("dog");
        Animal cat = AnimalFactory.create("cat");
        dog.speak();
        cat.speak();

        System.out.println("\n=== Observer (NewsAgency -> Channels) ===");
        NewsAgency agency = new NewsAgency();
        NewsChannel c1 = new NewsChannel("Channel-1");
        NewsChannel c2 = new NewsChannel("Channel-2");
        agency.addObserver(c1);
        agency.addObserver(c2);
        agency.setNews("Design patterns are awesome!");

        System.out.println("\n=== Adapter (OldPayment -> NewPaymentProcessor) ===");
        OldPaymentSystem old = new OldPaymentSystem();
        NewPaymentProcessor processor = new OldPaymentAdapter(old);
        processor.process(99.99);

        System.out.println("\n=== Command (Light & Remote) ===");
        Light livingRoom = new Light("Living Room");
        Command lightOn = new LightOnCommand(livingRoom);
        Command lightOff = new LightOffCommand(livingRoom);
        RemoteInvoker invoker = new RemoteInvoker();
        invoker.setCommand(lightOn);
        invoker.press();
        invoker.setCommand(lightOff);
        invoker.press();

        System.out.println("\n=== Memento (Editor undo) ===");
        Editor editor = new Editor();
        editor.type("Hello");
        editor.save();
        editor.type(" World");
        editor.show();
        editor.restore();
        editor.show();

        System.out.println("\n=== Strategy (SortContext) ===");
        List<Integer> list = Arrays.asList(5,3,8,1,2);
        SortContext ctx = new SortContext(new BubbleSortStrategy());
        System.out.println("Before: " + list);
        ctx.sort(new ArrayList<>(list));
        ctx.setStrategy(new QuickSortStrategy());
        ctx.sort(new ArrayList<>(list));

        System.out.println("\n=== Facade (HomeTheater) ===");
        HomeTheaterFacade home = new HomeTheaterFacade(new Amplifier(), new Projector(), new Lights());
        home.watchMovie("Interstellar");
        home.endMovie();

        System.out.println("\n=== Iterator (NameRepository) ===");
        NameRepository names = new NameRepository();
        names.add("Alice"); names.add("Bob"); names.add("Charlie");
        Iterator<String> it = names.iterator();
        while (it.hasNext()) System.out.println("Name: " + it.next());

        System.out.println("\n=== Composite (Graphics) ===");
        Graphic leaf1 = new Dot("Red Dot");
        Graphic leaf2 = new Circle("Blue Circle");
        CompositeGraphic picture = new CompositeGraphic("Picture");
        picture.add(leaf1);
        picture.add(leaf2);
        picture.render();

        System.out.println("\nDemo finished.");
        logger.log("Application finished");
    }
}

/* ------------------------- Singleton ------------------------- */
class Logger {
    private static Logger instance;
    private Logger() {}
    public static synchronized Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }
    public void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}

/* ------------------------- Factory ------------------------- */
interface Animal { void speak(); }
class Dog implements Animal { public void speak(){ System.out.println("Dog: woof"); } }
class Cat implements Animal { public void speak(){ System.out.println("Cat: meow"); } }
class AnimalFactory {
    public static Animal create(String type) {
        switch(type.toLowerCase()) {
            case "dog": return new Dog();
            case "cat": return new Cat();
            default: throw new IllegalArgumentException("Unknown animal: " + type);
        }
    }
}

/* ------------------------- Observer ------------------------- */
interface ObserverP { void update(String news); }
interface SubjectP { void addObserver(ObserverP o); void removeObserver(ObserverP o); void notifyObservers(); }
class NewsAgency implements SubjectP {
    private final List<ObserverP> observers = new ArrayList<>();
    private String news;
    public void addObserver(ObserverP o){ observers.add(o); }
    public void removeObserver(ObserverP o){ observers.remove(o); }
    public void notifyObservers(){ for (ObserverP o: observers) o.update(news); }
    public void setNews(String n){ this.news = n; notifyObservers(); }
}
class NewsChannel implements ObserverP {
    private final String name; public NewsChannel(String name){ this.name = name; }
    public void update(String news){ System.out.println(name + " received news: " + news); }
}

/* ------------------------- Adapter ------------------------- */
class OldPaymentSystem {
    public void makePaymentInDollars(double d){ System.out.println("Old system: paid $" + d); }
}
interface NewPaymentProcessor { void process(double amount); }
class OldPaymentAdapter implements NewPaymentProcessor {
    private final OldPaymentSystem adaptee;
    public OldPaymentAdapter(OldPaymentSystem adaptee){ this.adaptee = adaptee; }
    public void process(double amount){ adaptee.makePaymentInDollars(amount); }
}

/* ------------------------- Command ------------------------- */
interface Command { void execute(); }
class Light {
    private final String location;
    public Light(String location){ this.location = location; }
    public void on(){ System.out.println(location + " light is ON"); }
    public void off(){ System.out.println(location + " light is OFF"); }
}
class LightOnCommand implements Command {
    private final Light light; public LightOnCommand(Light light){ this.light = light; }
    public void execute(){ light.on(); }
}
class LightOffCommand implements Command {
    private final Light light; public LightOffCommand(Light light){ this.light = light; }
    public void execute(){ light.off(); }
}
class RemoteInvoker {
    private Command slot;
    public void setCommand(Command c){ slot = c; }
    public void press(){ if (slot!=null) slot.execute(); }
}

/* ------------------------- Memento ------------------------- */
class Editor {
    private StringBuilder content = new StringBuilder();
    private Deque<Memento> history = new ArrayDeque<>();
    public void type(String words){ content.append(words); }
    public void save(){ history.push(new Memento(content.toString())); }
    public void restore(){ if (!history.isEmpty()) content = new StringBuilder(history.pop().getState()); }
    public void show(){ System.out.println("Editor content: '" + content.toString() + "'"); }
    private static class Memento { private final String state; Memento(String s){ state = s; } String getState(){ return state; } }
}

/* ------------------------- Strategy ------------------------- */
interface SortStrategy { void sort(List<Integer> data); }
class BubbleSortStrategy implements SortStrategy {
    public void sort(List<Integer> data){
        List<Integer> a = data; int n = a.size();
        for (int i=0;i<n-1;i++) for (int j=0;j<n-i-1;j++) if (a.get(j)>a.get(j+1)) Collections.swap(a,j,j+1);
        System.out.println("Bubble sorted: " + a);
    }
}
class QuickSortStrategy implements SortStrategy {
    public void sort(List<Integer> data){
        List<Integer> a = new ArrayList<>(data);
        quicksort(a,0,a.size()-1);
        System.out.println("Quick sorted: " + a);
    }
    private void quicksort(List<Integer> a,int l,int r){ if (l<r){ int p = partition(a,l,r); quicksort(a,l,p-1); quicksort(a,p+1,r);} }
    private int partition(List<Integer> a,int l,int r){ int pivot = a.get(r); int i = l; for (int j=l;j<r;j++) if (a.get(j)<=pivot){ Collections.swap(a,i,j); i++; } Collections.swap(a,i,r); return i; }
}
class SortContext {
    private SortStrategy strategy;
    public SortContext(SortStrategy s){ this.strategy = s; }
    public void setStrategy(SortStrategy s){ this.strategy = s; }
    public void sort(List<Integer> data){ strategy.sort(data); }
}

/* ------------------------- Facade ------------------------- */
class Amplifier { void on(){ System.out.println("Amplifier on"); } void off(){ System.out.println("Amplifier off"); } }
class Projector { void on(){ System.out.println("Projector on"); } void off(){ System.out.println("Projector off"); } }
class Lights { void dim(int level){ System.out.println("Lights dimmed to " + level + "%"); } void on(){ System.out.println("Lights on"); } }
class HomeTheaterFacade {
    private final Amplifier amp; private final Projector proj; private final Lights lights;
    public HomeTheaterFacade(Amplifier amp, Projector proj, Lights lights){ this.amp = amp; this.proj = proj; this.lights = lights; }
    public void watchMovie(String movie){ System.out.println("Get ready to watch '" + movie + "'..."); lights.dim(20); amp.on(); proj.on(); }
    public void endMovie(){ System.out.println("Shutting movie theater down..."); proj.off(); amp.off(); lights.on(); }
}

/* ------------------------- Iterator ------------------------- */
class NameRepository implements Iterable<String> {
    private final List<String> names = new ArrayList<>();
    public void add(String name){ names.add(name); }
    public Iterator<String> iterator(){ return names.iterator(); }
}

/* ------------------------- Composite ------------------------- */
interface Graphic { void render(); }
class Dot implements Graphic {
    private final String name; public Dot(String name){ this.name = name; }
    public void render(){ System.out.println("Rendering dot: " + name); }
}
class Circle implements Graphic {
    private final String name; public Circle(String name){ this.name = name; }
    public void render(){ System.out.println("Rendering circle: " + name); }
}
class CompositeGraphic implements Graphic {
    private final String name; private final List<Graphic> children = new ArrayList<>();
    public CompositeGraphic(String name){ this.name = name; }
    public void add(Graphic g){ children.add(g); }
    public void remove(Graphic g){ children.remove(g); }
    public void render(){ System.out.println("Composite: " + name); for (Graphic g: children) g.render(); }
}
