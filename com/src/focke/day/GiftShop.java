package focke.day;

import focke.base.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiftShop extends Day {
   public GiftShop() throws IOException {
      super(2);
   }

   @Override
   protected void solveOne() throws IOException {
      List<String> actualInput = cleanInput(input);
      long result = actualInput.stream().mapToLong(this::checkInvalidValue).sum();
      printResult(1, result);
   }

   @Override
   protected void solveTwo() throws IOException {
      List<String> actualInput = cleanInput(input);
      long result = actualInput.stream().mapToLong(this::checkInvalidRange).sum();
      printResult(2, result);
   }


   @Override
   protected boolean runTestOne() throws IOException {
      assert(33 == checkInvalidValue("11-22")) : "Should be 33";
      assert(99 == checkInvalidValue("95-115")) : "Should be 99";
      assert(1010 == checkInvalidValue("998-1012")) : "Should be 1010";
      assert(222222 == checkInvalidValue("222220-222224")) : "Should be 222222";
      assert(0 == checkInvalidValue("1698522-1698528")) : "Should be 0";
      assert(446446 == checkInvalidValue("446443-446449")) : "Should be 446443";
      assert(38593859 == checkInvalidValue("38593856-38593862")) : "Should be 38593856";
      assert(188511885 == checkInvalidValue("188511880-1188511890")) :  "Should be 188511885";
      return true;
   }

   @Override
   protected boolean runTestTwo() throws IOException {
      assert(33 == checkInvalidRange("11-22")):"Should be 33";
      assert((99 + 111) == checkInvalidRange("95-115")) : "Should be 210";
      assert((999 + 1010) == checkInvalidRange("998-1012")) :  "Should be 999+1010";
      assert(188511885 == checkInvalidRange("188511880-1188511890")) : "Should be 188511885";
      assert(222222 == checkInvalidRange("222220-222224")) :  "Should be 222222";
      assert(0 == checkInvalidValue("1698522-1698528")) : "Should be 0";
      assert(446446 == checkInvalidRange("446443-446449")) : "Should be 446443";
      assert(38593859 == checkInvalidRange("38593856-38593862")) :  "Should be 38593856";
      assert(565656 == checkInvalidRange("565653-565659")) :  "Should be 565653";
      assert(2121212121 == checkInvalidRange("2121212118-2121212124")) : "Should be 2121212121";
      return true;
   }


   private long checkInvalidValue(String arg){
      long value = 0;
      String[] line = arg.split("-");
      long start = Long.parseLong(line[0]);
      long end = Long.parseLong(line[1]);
      for (long i = start; i <= end; ++i) {
         String iAsAString = String.valueOf(i);
         String left = iAsAString.substring(0, iAsAString.length() / 2);
         String right = iAsAString.substring(iAsAString.length() / 2);
         if (left.equals(right)) {
            value += i;
         }
      }
      return value;
   }

   private long checkInvalidRange(String arg){
      long value = 0;
      String[] line = arg.split("-");
      long start = Long.parseLong(line[0]);
      long end = Long.parseLong(line[1]);
      for (long i = start; i <= end; ++i) {
         String iAsAString = String.valueOf(i);
         if (isRepeating(iAsAString)) {
            value += i;
         }
      }
      return value;

   }

   protected boolean isRepeating(String arg){
      long length = arg.length();

      for (int i = 1; i <= length / 2 ; ++i) {
         if (length % i == 0) {

            String curSubString = arg.substring(0, i);
            long rep = length / i;
            // -> Concet longer because String actual in mmry
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < rep; ++j) {
               builder.append(curSubString);
            }

            if (builder.toString().equals(arg)) {
               return true;
            }
         }
      }
      return false;
   }


   private List<String> cleanInput(List<String> input) {
      List<String[]> cleanerInput = input.stream().map(line -> line.split(",")).toList();
      return Arrays.stream(cleanerInput.getFirst()).toList();
   }




}
