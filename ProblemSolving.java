import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProblemSolving {

    public static void main(String[] args) {
        proceed();
    }
    
    public static void proceed() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();
        if(isValidInputString(inputString)) {
            System.out.println(extractSubstringsAndReverse(inputString));
        }else {
            System.out.println("invalid input string please enter valid string");
            proceed();
        }
        scanner.close();
    }
    
    private static boolean isValidInputString(String input){
        if(input.isEmpty() || input.length() > 2000)
            return false;

        if (!input.matches("[a-z()]+")) {
            return false;
        }

        if(!isBalanced(input)) {
            return false;
        }

        return true;
    }
    
    private static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                    return false;

            }
        }
        return stack.isEmpty();
    }

    public static String extractSubstringsAndReverse(String input) {
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            input=input.replace(matcher.group(1),reverse(matcher.group(1)));
        }

        return input;
    }

    public static String reverse(String input) {
        StringBuilder reversed = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(input.charAt(i));
        }
        return reversed.toString();
    }
    
}

