

import com.hankcs.hanlp.HanLP;

import com.hankcs.hanlp.dictionary.CustomDictionary;

import com.hankcs.hanlp.seg.common.Term;
import org.jsoup.Jsoup;



import java.util.ArrayList;



import java.util.Collections;

import java.util.List;

import java.util.stream.Collectors;



public class SimilarityUtil {


    public static void main(String[] args) {
        double de = getSimilarity("北京市顺义区后沙峪地区","北京市顺义区顺义区后沙峪地区xx村");
        System.out.println(de);
    }

    /**
     * 获得两个句子的相似度
     *
     * @param sentence1
     * @param sentence2
     * @return
     */

    public static double getSimilarity(String sentence1, String sentence2) {

        List<Term> sent1Words = getSplitWords(sentence1);

        System.out.println(sent1Words);

        List<Term> sent2Words = getSplitWords(sentence2);

        System.out.println(sent2Words);

        List<Term> allWords = mergeList(sent1Words, sent2Words);


        int[] statistic1 = statistic(allWords, sent1Words);

        int[] statistic2 = statistic(allWords, sent2Words);


        double dividend = 0;

        double divisor1 = 0;

        double divisor2 = 0;

        for (int i = 0; i < statistic1.length; i++) {

            dividend += statistic1[i] * statistic2[i];

            divisor1 += Math.pow(statistic1[i], 2);

            divisor2 += Math.pow(statistic2[i], 2);

        }


        return dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2));

    }


    private static int[] statistic(List<Term> allWords, List<Term> sentWords) {

        int[] result = new int[allWords.size()];

        for (int i = 0; i < allWords.size(); i++) {

            result[i] = Collections.frequency(sentWords, allWords.get(i));

        }

        return result;

    }


    private static List<Term> mergeList(List<Term> list1, List<Term> list2) {

        List<Term> result = new ArrayList<>();

        result.addAll(list1);

        result.addAll(list2);

        return result.stream().distinct().collect(Collectors.toList());

    }


    private static List<Term> getSplitWords(String sentence) {

        // 标点符号会被单独分为一个 Term ，去除之

        return HanLP.segment(sentence);//.stream().map(a -> a.word).filter(s -> !"`~!@#$^&*()=|{}':;',\\[\\].<>/?~ ！ @# ￥…… &* （）—— |{} 【】‘；：”“ ' 。，、？ ".contains(s)).collect(Collectors.toList());

    }

}