import model.Dish;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * Created by maomao on 16/10/11.
 */
public class JavaStream {
    public static void main(String[] args) {

        /**
         * init data
         */
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french", false, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        /**
         * 4.2
         */
        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(toList());
        System.out.println("4.2 " + threeHighCaloricDishNames);

        /**
         * 4.4.1
         * 中间操作
         */
        List<String> names =
                menu.stream()
                        .filter(d -> {
                            System.out.println("filtering " + d.getName());
                            return d.getCalories() > 300;
                        })
                        .map(d -> {
                            System.out.println("mapping " + d.getName());
                            return d.getName();
                        })
                        .limit(3)
                        .collect(toList());
        System.out.println("4.4.1 " + names);

        /**
         * 5.1.2
         * distinct()
         */
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        /**
         * 5.1.4
         * 跳过元素(skip)
         */
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());

        /**
         * 5.4.1
         * 归约(reduce)
         */
        List<Integer> members = Arrays.asList(1, 2, 3, 4);
        int sum = members.stream()
                .reduce(0, Integer::sum);//和下面的一行效果一样
//            .reduce(0, (a, b) -> a + b);
        System.out.println("5.4.1: " + sum);

        /**
         * 6.3
         * 分组
         * 把菜按类型分类
         */
        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("dishesByType : " + dishesByType);

        /**
         * 6.3.2
         * 统计每类菜的个数
         */
        Map<Dish.Type, Long> typesCount = menu.stream()
                .collect(groupingBy(Dish::getType, counting()));
        System.out.println("typesCount : " + typesCount);

        /**
         * 6.3.2
         * 查找菜单中热量最高的菜肴,按类型搜集
         */
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))));
        System.out.println("mostCaloricByType : " + mostCaloricByType);

        /**
         * 6.3.2
         * 查找菜单中热量之和,按类型搜集
         */
        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                summingInt(Dish::getCalories)));
        System.out.println("totalCaloriesByType : " + totalCaloriesByType);

        /**
         * 6.3.2 扩展
         * 查找菜单中热量之和,最大,最小,平均,统计个数,按类型搜集
         */
        Map<Dish.Type, IntSummaryStatistics> sumCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                summarizingInt(Dish::getCalories)));
        System.out.println("sumCaloricByType : " + sumCaloricByType);
    }

}
