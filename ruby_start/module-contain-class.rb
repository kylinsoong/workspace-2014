module Awestruct

  class TiltMatcher
      def hello(name)
        puts "Hello #{name}"
      end
  end

end

test = Awestruct::TiltMatcher.new

test.hello("Ruby")
