### bundler runtime warning

Execute `awestruct -d` commands threw warning:

~~~
/usr/local/rvm/gems/ruby-2.0.0-p481/gems/bundler-1.6.3/lib/bundler/runtime.rb:222: warning: Insecure world writable dir /home/kylin/work/tools in PATH, mode 040777
~~~

[bundler-runtime-test.rb](bundler-runtime-test.rb)

Remove `/home/kylin/work/tools` referred configuration from system path the waring disappear.


### cannot load such file -- redcarpet

Execute `awestruct -d` commands threw error:

~~~
/usr/local/rvm/gems/ruby-2.0.0-p481/gems/tilt-2.0.1/lib/tilt/redcarpet.rb:2:in `require': cannot load such file -- redcarpet (LoadError)
	from /usr/local/rvm/gems/ruby-2.0.0-p481/gems/tilt-2.0.1/lib/tilt/redcarpet.rb:2:in `<top (required)>'
	from /usr/local/rvm/gems/ruby-2.0.0-p481/gems/tilt-2.0.1/lib/tilt/mapping.rb:237:in `require'
	from /usr/local/rvm/gems/ruby-2.0.0-p481/gems/tilt-2.0.1/lib/tilt/mapping.rb:237:in `block in lazy_load'
	from /usr/local/rvm/gems/ruby-2.0.0-p481/gems/tilt-2.0.1/lib/tilt/mapping.rb:234:in `each'
	from /usr/local/rvm/gems/ruby-2.0.0-p481/gems/tilt-2.0.1/lib/tilt/mapping.rb:234:in `lazy_load'
	from /usr/local/rvm/gems/ruby-2.0.0-p481/gems/tilt-2.0.1/lib/tilt/mapping.rb:214:in `lookup'
	from /usr/local/rvm/gems/ruby-2.0.0-p481/gems/tilt-2.0.1/lib/tilt/mapping.rb:152:in `[]'
	from /usr/local/rvm/gems/ruby-2.0.0-p481/gems/tilt-2.0.1/lib/tilt.rb:48:in `[]'
~~~

* [module-test.rb](module-test.rb) 
* [module-in-module-test.rb](module-in-module-test.rb)
* [module-contain-class.rb](module-contain-class.rb)
* [module-module-class.rb](module-module-class.rb)
* [redcarpet-reproduce.rb](redcarpet-reproduce.rb)
