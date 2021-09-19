$(document).ready(function() {
  //animal form validation
  $("form[name='endangeredForm']").validate({
    rules: {
      wildlifeName: "required",
      rangerName: "required",
      location: "required",
      health:"required",
      age:"required"
    },
    messages:{
    wildlifeName:"Wildlife category must be specified",
    rangerName:"Please indicate the rangers name",
    location:"Location is required",
    health:"Wildlife health life is required",
    age:"Indicate age of endangered wildlife"
    },
     submitHandler: function(form) {
          form.submit();
        }
  });

  $("form[name='animalForm']").validate({
     rules: {
        animalName: "required",
        animalRangerName: "required",
        animalLocation: "required"
      },
      messages:{
      animalName:"Wildlife category name must be specified",
      animalRangerName:"Please indicate the rangers name",
      animalLocation:"Location is required"
      },
       submitHandler: function(form) {
            form.submit();
          }
    });

  })

