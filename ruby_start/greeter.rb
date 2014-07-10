class Greeter
    def initialize(name = "World")
        @name = name
    end

    def say_hi
        puts "Hi #{@name}!"
    end

    def say_bye
        puts "Bye #{@name}, come back soon."
    end
end

puts ""
puts "initialize with empty name"
g1 = Greeter.new()
g1.say_hi
g1.say_bye
puts ""

puts "initialize with name value 'Kylin'"
g1 = Greeter.new("Kylin")
g1.say_hi
g1.say_bye
puts ""

puts "Greeter.instance_methods"
puts Greeter.instance_methods
puts ""

puts "Greeter.instance_methods(false)"
puts Greeter.instance_methods(false)
