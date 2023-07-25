package praktikum;

/**
 * Модель булочки для бургера.
 * Булочке можно дать название и назначить цену.
 */
public class Bun {

    private static final float EPS = (float)0.00000001;

    public String name;
    public float price;

    public Bun(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bun)) {
            return false;
        }
        Bun bun = (Bun)obj;
        if (name == null) {
            if (bun.getName() != null) {
                return false;
            } else {
                return Math.abs(price - bun.getPrice()) < EPS;
            }
        }
        return name.equals(bun.getName()) && Math.abs(price - bun.getPrice()) < EPS;
    }
}