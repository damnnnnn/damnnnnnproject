package HunterGame;

public class Animal {
    public int lifevalue=100;

    public  void attack(Hunter h){
        h.lifeValue -= 60-lifevalue/100;
        System.out.println("恶龙咆哮...");
        System.out.println("动物造成了"+(60-lifevalue/100)+"伤害");
    }
}