hairs = ['brown', 'blond', 'red']
eyes = ['brown', 'blue', 'green']
weights = [1, 2, 3, 4]

puts hairs
puts

puts eyes
puts

puts weights
puts

the_count = [1, 2, 3, 4, 5]
fruits = ['apples', 'oranges', 'pears', 'apricots']
change = [1, 'pennies', 2, 'dimes', 3, 'quarters']

for number in the_count
  puts "This is count #{number}"
end

puts

for hair in hairs
  puts "This is #{hair} hair"
end

puts

for eye in eyes
  puts "This is #{eye} eye"
end

puts

for w in weights
  puts "The weight is: #{w}"
end

fruits.each do |fruit|
  puts "A fruit of type: #{fruit}"
end

hairs.each do |hair|
  puts "This is #{hair} hair"
end

eyes.each do |eye|
  puts "This is #{eye} eye"
end

weights.each do |w|
  puts "The weight is: #{w}"
end

for i in change
  puts "I got #{i}"
end

elements = []

for i in (0..5)
  puts "Adding #{i} to the list."
  # push is a function that arrays understand
  elements.push(i)
end

for i in elements
  puts "Element was: #{i}"
end
