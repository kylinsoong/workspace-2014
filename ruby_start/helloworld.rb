class HelloWorld

    def h(name = "World")
	puts "Hello #{name.capitalize}!"
    end
end

hello = HelloWorld.new()
hello.h()
hello.h("Kylin")
