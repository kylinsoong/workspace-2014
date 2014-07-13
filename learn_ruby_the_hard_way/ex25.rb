module Ex25

  def self.break_words(stuff)
    words = stuff.split(' ')
    words
  end

  def self.sort_words(words)
    words.sort()
  end

  def self.print_first_word(words)
    word = words.shift()
    puts word
  end

  def self.print_last_word(words)
    word = words.pop()
    puts word
  end

  def self.sort_sentence(sentence)
    words = break_words(sentence)
    sort_words(words)
  end

  def self.print_first_and_last(sentence)
    words = break_words(sentence)
    print_first_word(words)
    print_last_word(words)
  end

  def self.print_first_and_last_sorted(sentence)
    words = sort_sentence(sentence)
    print_first_word(words)
    print_last_word(words)
  end
end

sentence = "All good things come to those who wait."

words = Ex25.break_words(sentence)
puts words
puts

sorted_words = Ex25.sort_words(words)
puts sorted_words
puts

Ex25.print_first_word(words)
puts

Ex25.print_last_word(words)
puts

Ex25.print_first_word(sorted_words)
puts

Ex25.print_last_word(sorted_words)
puts

Ex25.sort_sentence(sentence)
puts

Ex25.print_first_and_last(sentence)
puts

Ex25.print_first_and_last_sorted(sentence)
puts

