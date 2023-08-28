package example1;

import java.util.List;
import java.util.function.BiPredicate;

public class Finder {
    private static <T extends Comparable<T>> T finder(List<T> data, BiPredicate<T, T> condition){
          T result = data.get(0);
          for(int i = 1; i< data.size(); i++){
              T item = data.get(i);
              if(condition.test(item, result)) result = item;
              //result = condition.test(item, result) ? item : result;
          }
          return result;
    }

    public static <T extends Comparable<T>> T max(List<T> data) {
        return finder(data, (item, max) -> item.compareTo(max) > 0);
    }

    public static <T extends Comparable<T>> T min(List<T> data) {
        return finder(data, (item, min) -> item.compareTo(min) < 0);
    }

}
