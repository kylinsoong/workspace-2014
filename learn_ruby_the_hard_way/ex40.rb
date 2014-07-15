mystuff = {'apple' => "I AM APPLES!"}
puts mystuff['apple']
puts

require './mystuff'

MyStuff.apple()
puts MyStuff::TANGERINE
puts

class MyStuffs
  def initialize()
    @tangerine = "And now a thousand years between"
  end

  attr_reader :tangerine

  def apple()
    puts "I AM CLASSY APPLES!"
  end
end

thing = MyStuffs.new()
thing.apple()
puts thing.tangerine
puts

class Song

    def initialize(lyrics)
        @lyrics = lyrics
    end

    def sing_me_a_song()
        for line in @lyrics
            puts line
        end
    end
end

happy_bday = Song.new(["Happy birthday to you", "I don't want to get sued", "So I'll stop right there"])
bulls_on_parade = Song.new(["They rally around the family", "With pockets full of shells"])

happy_bday.sing_me_a_song()

puts

bulls_on_parade.sing_me_a_song()
