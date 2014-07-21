module Awestruct
  module Handlers

    def self.hello(name)
      puts "Hello #{name}"
    end

  end
end

Awestruct::Handlers.hello("Ruby")
