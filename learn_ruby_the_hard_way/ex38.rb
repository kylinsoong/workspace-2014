ten_things = "Apples Oranges Crows Telephone Light Sugar"
puts "Wait there's not 10 things in that list, let's fix that."
stuff = ten_things.split(' ')
puts stuff

puts

more_stuff = %w(Day Night Song Frisbee Corn Banana Girl Boy)
puts more_stuff

puts

while stuff.length != 10
  next_one = more_stuff.pop()
  puts "Adding: #{next_one}"
  stuff.push(next_one)
  puts "There's #{stuff.length} items now."
end

puts

puts "There we go: #{stuff}"

puts

puts "Let's do some things with stuff."

puts

puts stuff[1]
puts stuff[-1]
puts stuff.pop()
puts stuff.join(' ')
puts stuff.join(' - ')
puts stuff.values_at(3,5).join('#')
