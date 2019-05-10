package HunterGame;

public class Hunter {
    int lifeValue = 100;
    public void eat(Food f){
        this.lifeValue+=f.power/2;
        System.out.println("能量补充中...");
        System.out.println("猎人回复了"+f.power/2+"血量");
    }
    public void hunt(Animal a){
        a.lifevalue-=30;
        System.out.println("勇敢的猎人打猎中...");
        System.out.println("猎人造成了30伤害");
    }
}
