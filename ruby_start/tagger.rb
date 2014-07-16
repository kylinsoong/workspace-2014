class TagStat
        def initialize(tag)
          @tag   = tag
        end

        def to_s
          @tag
        end
end

tag = TagStat.new("tag-1")
puts tag.to_s
