import java.util.*;

public class main {
    public static void main(String[] args) {
        System.out.println("hello");
        List<String> strings = new ArrayList<>(Arrays.asList("a", "aba", "aab", "sealaaa", "sealaaaalion", "eepplppee", "abb", "bb", "ibvjkmpyzsifuxcabqqpahjdeuzaybqsrsmbfplxycsafogotliyvhxjtkrbzqxlyfwujzhkdafhebvsdhkkdbhlhmaoxmbkqiwiusngkbdhlvxdyvnjrzvxmukvdfobzlmvnbnilnsyrgoygfdzjlymhprcpxsnxpcafctikxxybcusgjwmfklkffehbvlhvxfiddznwumxosomfbgxoruoqrhezgsgidgcfzbtdftjxeahriirqgxbhicoxavquhbkaomrroghdnfkknyigsluqebaqrtcwgmlnvmxoagisdmsokeznjsnwpxygjjptvyjjkbmkxvlivinmpnpxgmmorkasebngirckqcawgevljplkkgextudqaodwqmfljljhrujoerycoojwwgtklypicgkyaboqjfivbeqdlonxeidgxsyzugkntoevwfuxovazcyayvwbcqswzhytlmtmrtwpikgacnpkbwgfmpavzyjoxughwhvlsxsgttbcyrlkaarngeoaldsdtjncivhcfsaohmdhgbwkuemcembmlwbwquxfaiukoqvzmgoeppieztdacvwngbkcxknbytvztodbfnjhbtwpjlzuajnlzfmmujhcggpdcwdquutdiubgcvnxvgspmfumeqrofewynizvynavjzkbpkuxxvkjujectdyfwygnfsukvzflcuxxzvxzravzznpxttduajhbsyiywpqunnarabcroljwcbdydagachbobkcvudkoddldaucwruobfylfhyvjuynjrosxczgjwudpxaqwnboxgxybnngxxhibesiaxkicinikzzmonftqkcudlzfzutplbycejmkpxcygsafzkgudy"));
        strings.forEach(s -> {
            System.out.println(s + " " + longestPalindromicSubstring(s));
        });
    }

    public static int longestPalindromicSubstring(String string){
        if(string.length() <= 1) return string.length();
        List<Integer> max = new ArrayList<>();
        max.add(1);
        List<String> longest = new ArrayList<>(Arrays.asList(""));
        Map<List<Integer>, Boolean> palindromeChecks = new HashMap<>();
        for(int i = 0; i < string.length(); i++){
            palindromeChecks.put(Arrays.asList(i, i), true);
        }
        for(int i = 0; i < string.length() - 1; i++){
            palindromeChecks.put(Arrays.asList(i, i + 1), string.charAt(i) == string.charAt(i + 1));
        }
        for(int i = 2; i < string.length(); i++){
            for(int j = 0; j < string.length() - i; j++){
                List<Integer> ints = new ArrayList<>(Arrays.asList(j, j + i));
                palindromeChecks.put(ints, string.charAt(j) == string.charAt(j + i)
                        && palindromeChecks.get(new ArrayList<>(Arrays.asList(j + 1,j + i - 1))));
            }
        }
        if(string.length() > 2) {
            palindromeChecks.put(new ArrayList<Integer>(Arrays.asList(0, string.length() - 1)),
                    string.charAt(0) == string.charAt(string.length() - 1) &&
                            palindromeChecks.get(new ArrayList<>(Arrays.asList(1, string.length() - 2))));
        }
        palindromeChecks.entrySet().forEach(e -> {
            if(e.getValue() == true){
                max.set(0, Math.max(max.get(0), e.getKey().get(1) - e.getKey().get(0) + 1));
                if(string.substring(e.getKey().get(0), e.getKey().get(1) + 1).length() > longest.get(0).length()){
                    longest.set(0, string.substring(e.getKey().get(0), e.getKey().get(1) + 1));
                }
            }
        });
        System.out.println(longest.get(0));
        return max.get(0);
    }
}
