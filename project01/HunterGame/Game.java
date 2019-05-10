package HunterGame;

//定义一个人 有攻击动物的方法 有获取食物的方法
//定义一个动物 有攻击人的方法
//Math.random() 0~1 产生一个随机数
public class Game {
    public static void main(String[] args) {
        Hunter h = new Hunter();
        Animal a = new Animal();

        while (true){
            int ran = (int)(10*Math.random());
            if (ran%2 ==0){
                h.hunt(a);
                System.out.println("动物生命值:"+a.lifevalue);
            }
            else {
                a.attack(h);

                System.out.println("猎人生命值:"+h.lifeValue);
            }

            if (ran<=5){
                Food f = new Food();
                h.eat(f);
                System.out.println("猎人的生命值："+h.lifeValue);
            }

            if (h.lifeValue<=0 ||a.lifevalue<=0){
                break;
            }
        }
        System.out.println("猎人:"+h.lifeValue);
        System.out.println("动物:"+a.lifevalue);
    }

}
