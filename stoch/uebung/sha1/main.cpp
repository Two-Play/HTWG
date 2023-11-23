#include <iostream>
#include <chrono>
#include <openssl/sha.h>
#include <cstring>

using namespace std::chrono;

std::string sha1(const std::string &input) {
    unsigned char hash[SHA_DIGEST_LENGTH];
    SHA1(reinterpret_cast<const unsigned char *>(input.c_str()), input.length(), hash);

    char sha1_hash[SHA_DIGEST_LENGTH * 2 + 1];
    sha1_hash[SHA_DIGEST_LENGTH * 2] = '\0';
    for (int i = 0; i < SHA_DIGEST_LENGTH; ++i) {
        sprintf(&sha1_hash[i * 2], "%02x", hash[i]);
    }

    return std::string(sha1_hash);
}

bool crackSha1(const std::string &targetHash, const std::string &charset, int maxLength) {
    int charsetLength = charset.length();
    std::string password;

    // Brute-force loop
    for (int len = 1; len <= maxLength; ++len) {
        password.resize(len, charset[0]);

        do {
            std::string hashed = sha1(password);
            if (hashed == targetHash) {
                std::cout << "Password cracked: " << password << std::endl;
                return true;
            }
        } while (std::next_permutation(password.begin(), password.end(), [&charset](char &c1, char &c2) {
            return charset.find(c1) < charset.find(c2);
        }));
    }

    std::cout << "Password not found within the specified length and charset." << std::endl;
    return false;
}


int main() {
    auto start = high_resolution_clock::now();


    std::string targetHash = "4ad1926be45241079b7182a214a532d1aa62512b";
    std::string charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Characters to include
    int maxLength = 8; // Maximum length of the password

    crackSha1(targetHash, charset, maxLength);

    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<microseconds>(stop - start);

// To get the value of duration use the count()
// member function on the duration object
    std::cout << duration.count() << " ms" << std::endl;
    return 0;
}
