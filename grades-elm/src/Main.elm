module Main exposing (..)

import Html exposing (Html, Attribute, div, input, text, h2)
import Html.App as App
import Html.Attributes exposing (..)
import Html.Events exposing (onInput)
import String

main : Program Never
main =
    App.beginnerProgram
        { model = model
        , view = view
        , update = update
        }

type alias Model =
  {
    grade: String
  }

model : Model
model = {
    grade = "100"
  }

type Msg = UpdateGrade String

update : Msg -> Model -> Model
update msg model =
  case msg of
    UpdateGrade newGrade ->
      { model | grade = newGrade }

view : Model -> Html Msg
view model =
  div []
    [
      h2 [] [ text "Grades" ],
      div [] [ text ("Grade is " ++ model.grade) ],
      input [ type' "text", value model.grade, onInput UpdateGrade ] [] 
    ]
