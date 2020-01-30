package aws1;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

// CLASS BEGINS, THIS CLASS IS REQUIRED
class Solution {
  // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED

  private static class Feature implements Comparable<Feature> {
    public String name;
    public Integer count;

    public Feature(String name, Integer count) {
      this.name = name;
      this.count = count;
    }

    public int compareTo(Feature other) {
      int c = other.count.compareTo(this.count);
      if (c == 0) {
        c = this.name.compareTo(other.name);
      }
      return c;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((count == null) ? 0 : count.hashCode()) + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Feature other = (Feature) obj;
        if (count == null) {
            if (other.count != null)
                return false;
        }
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!count.equals(other.count))
            return false;
        else if (!name.equals(other.name))
            return false;
        return true;
    }


  }

  public List<String> rankFeatures(int topFeatures, int numOfFeatures, List<String> features, int numOfRequests, List<String> requests) {
    Map<String, Integer> mFeatures = features.stream().collect(Collectors.toMap(f -> f, f -> 0));
    requests.stream().map(r -> r.replaceAll("\\.|,", " ").replaceAll("  ", " ")).forEach(l -> {
      Set<String> alreadyUsed = new HashSet<>();
      String[] words = l.split(" ");
      for (String word : words) {
        mFeatures.computeIfPresent(word.toLowerCase(), (k, v) -> {
          if (alreadyUsed.contains(k)) {
            return v;
          }
          else {
            alreadyUsed.add(k);
            return v + 1;
          }
        });
      }
    });
    PriorityQueue<Feature> queue = new PriorityQueue<>();
    mFeatures.entrySet().stream().map(e -> new Feature(e.getKey(), e.getValue())).forEach(f -> queue.add(f));
    List<String> top = new ArrayList<>();
    for (int i = 0; i < topFeatures; i++) {
      Feature f = queue.poll();
      if (f != null) top.add(f.name); else break;
    }
    return top;
  }

  // METHOD SIGNATURE ENDS

  public static void main(String[] args) {
    Solution s = new Solution();
    List<String> features = Arrays.asList(new String[] {"display", "waterproof", "battery", "sun"});
    List<String> requests1 = Arrays.asList(new String[] {
        "I would like a better display under the sun." /*display, sun*/,
        "Waterproof, please waterproof. Waterproof is the must." /*waterproof*/,
        "The battery is to short and the display is to small." /*battery, display*/,
        "I cannot see well under the sun and I would like to swin with my kindle. Waterproof is essential" /*waterproof, sun*/,
        "Increase the display size, please." /*display*/});
    List<String> topFeatures = s.rankFeatures(2, 4, features, 5, requests1);
    System.out.println(topFeatures);
    List<String> requests2 = Arrays.asList(new String[] {
        "I would like a better display under the sun." /*display, sun*/,
        "Waterproof, please waterproof. Waterproof is the must." /*waterproof*/,
        "The battery is to short and the display is to small." /*battery, display*/,
        "I cannot see well under the sun and I would like to swin with my kindle. Waterproof is essential" /*waterproof, sun*/,
        "Increase the display size, please." /*display*/,
        "Waterproof is a must to have." /*waterproof*/});
    topFeatures = s.rankFeatures(2, 4, features, 6, requests2);
    System.out.println(topFeatures);
  }

}
