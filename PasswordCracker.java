import java.io.*;
import java.security.*;
import java.util.*;

public class PasswordCracker {

    // Compute MD5 hash
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b)); // Convert to hex
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    // Wordlist Attack
    public static void wordlistAttack(String targetHash, String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int attempts = 0;
        long start = System.currentTimeMillis();

        while ((line = reader.readLine()) != null) {
            attempts++;
            String hashed = md5(line.trim());
            if (hashed.equalsIgnoreCase(targetHash)) {
                long end = System.currentTimeMillis();
                System.out.println("✅ Password found: " + line);
                System.out.println("Attempts: " + attempts);
                System.out.println("Time: " + (end - start) + " ms");
                reader.close();
                return;
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("❌ Password not found in wordlist.");
        System.out.println("Attempts: " + attempts);
        System.out.println("Time: " + (end - start) + " ms");
        reader.close();
    }

    // Brute-force Attack (up to 4 chars)
    public static void bruteForceAttack(String targetHash, String charset, int maxLen) {
        long start = System.currentTimeMillis();
        int[] attempts = {0};

        for (int len = 1; len <= maxLen; len++) {
            if (brute("", targetHash, charset, len, attempts)) {
                long end = System.currentTimeMillis();
                System.out.println("Attempts: " + attempts[0]);
                System.out.println("Time: " + (end - start) + " ms");
                return;
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("❌ Password not found (max length " + maxLen + ")");
        System.out.println("Attempts: " + attempts[0]);
        System.out.println("Time: " + (end - start) + " ms");
    }

    // Recursive brute force
    public static boolean brute(String prefix, String targetHash, String charset, int maxLen, int[] attempts) {
        if (prefix.length() == maxLen) return false;

        for (int i = 0; i < charset.length(); i++) {
            String attempt = prefix + charset.charAt(i);
            attempts[0]++;
            if (md5(attempt).equalsIgnoreCase(targetHash)) {
                System.out.println("✅ Password found: " + attempt);
                return true;
            }

            if (brute(attempt, targetHash, charset, maxLen, attempts)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔐 Java Password Cracker (Educational Use Only)");
        System.out.print("Enter target MD5 hash: ");
        String targetHash = scanner.nextLine().trim();

        System.out.println("Choose attack type:");
        System.out.println("1 - Wordlist Attack");
        System.out.println("2 - Brute Force Attack");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            System.out.print("Enter path to wordlist (e.g., wordlist.txt): ");
            String wordlist = scanner.nextLine().trim();
            wordlistAttack(targetHash, wordlist);
        } else if (choice == 2) {
            System.out.print("Enter character set (e.g., abc123): ");
            String charset = scanner.nextLine().trim();
            System.out.print("Enter max password length (e.g., 4): ");
            int maxLen = scanner.nextInt();
            bruteForceAttack(targetHash, charset, maxLen);
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
