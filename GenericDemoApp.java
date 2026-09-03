GenericDemoApp.java


GenericDemoApp.java

1    //Adam Santana: Module 6 Generics Project: COP-3330C-16376
3    import java.util.ArrayList;
4    import java.util.Comparator;
5    import java.util.List;
7    //making the general utility class/generic type parameter
8    class GenericUtils {
9        public static <T> int indexOf(T[] arr, T value) {
10           if (arr == null) return -1;
11           for (int i = 0; i < arr.length; i++) {
12               if ((arr[i] == null && value == null) ||
13                       (arr[i] != null && arr[i].equals(value))) {
14                   return i;
15               }
16           }
17           return -1;
18       }
19   }
21   //making the general container class
22   class GenericContainer<T> {
23       private final List<T> data = new ArrayList<>();
25       public void add(T item) {
26           data.add(item);
27       }
29       public void remove(T item) {
30           data.remove(item);
31       }
33       public T get(int index) {
34           return data.get(index);
35       }
37       public void sort(Comparator<? super T> comparator) {
38           data.sort(comparator);
39       }
41       public void printAll() {
42           if (data.isEmpty()) {
43               System.out.println("empty");
44               return;
45           }
46           for (int i = 0; i < data.size(); i++) {
47               System.out.println(i + ": " + data.get(i));
48           }
49       }
51       public List<T> toList() {
52           return new ArrayList<>(data);
53       }
54   }
56   //Demon slayers class
57   class DemonSlayerCharacter {
58       private final String name;
59       private final String breathingStyle;
60       private final int powerLevel;
62       public DemonSlayerCharacter(String name, String breathingStyle, int powerLevel) {
63           this.name = name;
64           this.breathingStyle = breathingStyle;
65           this.powerLevel = powerLevel;
66       }
68       public int getPowerLevel() { return powerLevel; }
70       @Override
71       public String toString() {
72           return String.format("Slayer{name='%s', style='%s', power=%d}", name, breathingStyle, powerLevel);
73       }
74   }
76   class Demon {
77       private final String name;
78       private final String rank;
79       private final int dangerLevel;
81       public Demon(String name, String rank, int dangerLevel) {
82           this.name = name;
83           this.rank = rank;
84           this.dangerLevel = dangerLevel;
85       }
87       public int getDangerLevel() { return dangerLevel; }
89       public boolean isUpperMoon() {
90           return rank.toLowerCase().contains("upper");
91       }
93       @Override
94       public String toString() {
95           return String.format("Demon{name='%s', rank='%s', danger=%d}", name, rank, dangerLevel);
96       }
97   }
99   class Sword {
100      private final String color;
101      private final String ownerName;
102      private final double sharpness; //0 to 10 scale
104      public Sword(String color, String ownerName, double sharpness) {
105          this.color = color;
106          this.ownerName = ownerName;
107          this.sharpness = sharpness;
108      }
110      public double getSharpness() { return sharpness; }
111      //extra method
112      public boolean isBattleReady() {
113          return sharpness >= 8.0;
114      }
116      @Override
117      public String toString() {
118          return String.format("Sword{color='%s', owner='%s', sharpness=%.1f}", color, ownerName, sharpness);
119      }
120  }
121  //Generic demo application
122  public class GenericDemoApp {
123      public static void main(String[] args) {
124          System.out.println("demo");
126          Integer[] nums = {1, 3, 5, 7, 9};
127          String[] names = {"Tanjiro", "Nezuko", "Zenitsu", "Inosuke"};
128          Double[] powers = {10.5, 23.4, 42.0};
130          System.out.println("Index of 5: " + GenericUtils.indexOf(nums, 5));
131          System.out.println("Index of 'Nezuko': " + GenericUtils.indexOf(names, "Nezuko"));
132          System.out.println("Index of 23.4: " + GenericUtils.indexOf(powers, 23.4));
133          System.out.println("Index of 'Giyu': " + GenericUtils.indexOf(names, "Giyu")); // not found
135          System.out.println("\nGeneric data");
136          //slayers
137          GenericContainer<DemonSlayerCharacter> slayerContainer = new GenericContainer<>();
138          slayerContainer.add(new DemonSlayerCharacter("Tanjiro Kamado", "Water Breathing", 85));
139          slayerContainer.add(new DemonSlayerCharacter("Giyu Tomioka", "Water Breathing", 95));
140          slayerContainer.add(new DemonSlayerCharacter("Kyojuro Rengoku", "Flame Breathing", 97));
142          System.out.println("\nDemon Slayer Characters:");
143          slayerContainer.printAll();
145          System.out.println("Removing Tanjiro...");
146          slayerContainer.remove(slayerContainer.get(0));
147          slayerContainer.printAll();
149          System.out.println("\nSorting Slayers by Power Level:");
150          slayerContainer.sort(Comparator.comparingInt(DemonSlayerCharacter::getPowerLevel).reversed());
151          slayerContainer.printAll();
153        //demons
154          GenericContainer<Demon> demonContainer = new GenericContainer<>();
155          demonContainer.add(new Demon("Muzan Kibutsuji", "Host", 100));
156          demonContainer.add(new Demon("Akaza", "Upper Moon Three", 90));
157          demonContainer.add(new Demon("Doma", "Upper Moon Two", 95));
159          System.out.println("\nDemons:");
160          demonContainer.printAll();
162          System.out.println("Sorting Demons by Danger Level:");
163          demonContainer.sort(Comparator.comparingInt(Demon::getDangerLevel).reversed());
164          demonContainer.printAll();
166          System.out.println("\nUpper Moons Only:");
167          for (Demon d : demonContainer.toList()) {
168              if (d.isUpperMoon()) System.out.println(d);
169          }
171          //swords
172          GenericContainer<Sword> swordContainer = new GenericContainer<>();
173          swordContainer.add(new Sword("Black", "Tanjiro Kamado", 8.5));
174          swordContainer.add(new Sword("Flame Red", "Kyojuro Rengoku", 9.8));
175          swordContainer.add(new Sword("Blue", "Giyu Tomioka", 9.0));
177          System.out.println("\nNichirin Swords:");
178          swordContainer.printAll();
180          System.out.println("\nSwords by Sharpness:");
181          swordContainer.sort(Comparator.comparingDouble(Sword::getSharpness).reversed());
182          swordContainer.printAll();
184          System.out.println("\nSwords ready:");
185          for (Sword s : swordContainer.toList()) {
186              if (s.isBattleReady()) System.out.println(s);
187          }
189          System.out.println("\nDemo was completed");
190      }
191  }
