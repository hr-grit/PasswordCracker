# PasswordCracker

- A simple educational tool written in Java for cracking MD5 hashes using Wordlist Attack and Brute-Force Attack techniques.
- This program is intended for learning purposes only to demonstrate basic principles behind password security, hashing, and ethical hacking.

# Features

- Wordlist-based attack using a file of candidate passwords
- Recursive brute-force method for generating password guesses
- Real-time statistics:
- Attempt count
- Time taken
- Automatically stops when password is cracked
- MD5 hashing algorithm used for demonstration

# How It Works

The program offers two attack types:
1. Wordlist Attack
Compares the MD5 hash of each word in a file to the target hash.

3. Brute Force Attack
Recursively tries every combination of characters from a user-provided charset up to a defined max length.

# Limitations

- Only supports MD5 hashes
- Brute force is slow for passwords longer than 4–5 characters
- No multi-threading or GPU acceleration


## Future Enhancements

1) Algorithm Support
-Add support for other hashing algorithms: SHA-1, SHA-256, bcrypt

2) Performance Improvements
-Implement multi-threading for brute-force attacks
-Integrate dictionary pre-processing (sorting, deduplication)
-Allow GPU-based acceleration via libraries like OpenCL or CUDA (advanced)

3) UI and UX
 -Create a GUI version using JavaFX or Swing
 -Add progress bars and live feedback for long operations

4) Usability
-Save cracked passwords and statistics to a file
-Add input validation and error handling (e.g., invalid file path)
-Support config file or command-line arguments

5) Testing and Security
-Add unit tests for each module
-Log hash collisions and false positives
-Optionally allow salting and test its impact

