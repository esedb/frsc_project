
function readURL(input) {
            if (input.files && input.files[0]) {
              var reader = new FileReader();
              reader.onload = function(e) {
                $('.mimage').attr('src', e.target.result);
              };

              reader.readAsDataURL(input.files[0]);
            }
          }

$(".img_class").change(function() {
  readURL(this);
});
                                
                                

