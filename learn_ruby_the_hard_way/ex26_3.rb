module Ex25
# This function will break up words for us.
def self.break_words(stuff)
    words = stuff.split(' ')
    return words
end

# Sorts the words.
def self.sort_words(words)
    return words.sort()
end

# Prints the first word after popping it off.
def self.puts_first_word(words)
    word = words.shift()
    puts word
end

# Prints the last word after popping it off.
def self.puts_last_word(words)
    word = words.pop()
    puts word
end

# Takes in a full sentence and returns the sorted words.
def self.sort_sentence(sentence)
    words = break_words(sentence)
    return sort_words(words)
end

# Puts the first and last words of the sentence.
def self.puts_first_and_last(sentence)
    words = break_words(sentence)
    puts_first_word(words)
    puts_last_word(words)
end

# Sorts the words then prints the first and last one.
def self.puts_first_and_last_sorted(sentence)
    words = sort_sentence(sentence)
    puts_first_word(words)
    puts_last_word(words)
end
end

sentence = "All god\tthings come to those who weight."

words = Ex25.break_words(sentence)
sorted_words = Ex25.sort_words(words)

Ex25.puts_first_word(words)
Ex25.puts_last_word(words)
Ex25.puts_first_word(sorted_words)
Ex25.puts_last_word(sorted_words)

puts

sorted_words = Ex25.sort_sentence(sentence)
puts sorted_words

puts

Ex25.puts_first_and_last(sentence)

Ex25.puts_first_and_last_sorted(sentence)
