package chapter3.compare.comparator;

class DuckExtended implements Comparable<DuckExtended>{
    private int weight;
    private String name;

    public static void main(String[] args) {

    }

    public DuckExtended(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(DuckExtended o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "DuckExtended{" +
                "weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}

