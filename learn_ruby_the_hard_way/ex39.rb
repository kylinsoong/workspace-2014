things = ['a','b','c','d']
puts things[1]
things[1] = 'z'
puts things[1]
puts things.inspect
puts

stuff = {'name' => 'Kylin', 'age' => 29, 'height' => 175}
puts "stuff: #{stuff}"
puts stuff['name']
puts stuff['age']
puts stuff['height']
stuff['city'] = 'Beijing'
puts "stuff: #{stuff}"
puts stuff['city']

puts
stuff[1] = "Wow"
stuff[2] = "Neato"
puts stuff[1]
puts stuff[2]
puts stuff.inspect()

puts
stuff.delete('city')
stuff.delete(1)
stuff.delete(2)
puts stuff.inspect

puts

states = {
    'Oregon' => 'OR',
    'Florida' => 'FL',
    'California' => 'CA',
    'New York' => 'NY',
    'Michigan' => 'MI'
}

cities = {
    'CA' => 'San Francisco',
    'MI' => 'Detroit',
    'FL' => 'Jacksonville'
}

cities['NY'] = 'New York'
cities['OR'] = 'Portland'

puts '-' * 10
puts "NY State has: ", cities['NY']
puts "OR State has: ", cities['OR']

puts '-' * 10
puts "Michigan's abbreviation is: ", states['Michigan']
puts "Florida's abbreviation is: ", states['Florida']

puts '-' * 10
puts "Michigan has: ", cities[states['Michigan']]
puts "Florida has: ", cities[states['Florida']]

puts '-' * 10
for state, abbrev in states
    puts "%s is abbreviated %s" % [state, abbrev]
end

puts '-' * 10
for abbrev, city in cities
    puts "%s has the city %s" % [abbrev, city]
end

puts '-' * 10
for state, abbrev in states
    puts "%s state is abbreviated %s and has city %s" % [
        state, abbrev, cities[abbrev]]
end

puts '-' * 10
# if it's not there you get nil
state = states['Texas']

if not state
    puts "Sorry, no Texas."
end

# get a city with a default value
city = cities['TX'] || 'Does Not Exist'
puts "The city for the state 'TX' is: %s" % city
