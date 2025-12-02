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

   }


   @Override
   protected boolean runTestOne() throws IOException {
      assert(33 == checkInvalidValue("11-22"));
      assert(99 == checkInvalidValue("95-115"));
      assert(1010 == checkInvalidValue("998-1012"));
      assert(188511885 == checkInvalidValue("188511880-1188511890"));
      assert(222222 == checkInvalidValue("222220-222224"));
      assert(0 == checkInvalidValue("1698522-1698528"));
      assert(446446 == checkInvalidValue("446443-446449"));
      assert(38593859 == checkInvalidValue("38593856-38593862"));
      return true;
   }

   @Override
   protected boolean runTestTwo() throws IOException {
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

   private List<String> cleanInput(List<String> input) {
      List<String[]> cleanerInput = input.stream().map(line -> line.split(",")).toList();
      return Arrays.stream(cleanerInput.getFirst()).toList();
   }




}
