require 'tilt'

module Awestruct
  module Handlers
  
    class TiltMatcher

      def hello(name)
        puts "Hello #{name}"
      end

    end
  end
end



test = Awestruct::Handlers::TiltMatcher.new

puts test.hello("Ruby")
