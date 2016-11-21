module Main exposing (..)

import Html exposing (Html, Attribute, div, input, text, h2, span, table, tbody, tr, td)
import Html.App as App
import Html.Attributes exposing (..)
import Html.Events exposing (onInput)
import String
import Regex exposing (contains, regex)

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

parseableNumber : String -> Bool
parseableNumber s = contains (regex "^\\d+\\.*\\d*$") s

calculateUpperGrade : Float -> Float -> Float -> Float
calculateUpperGrade grade lowerBound delta =
  let
    oneHigherLowerBound = grade * (lowerBound + 0.01)
    deltaAmount =
      if delta == 0
      then 1
      else 0.1
  in
    if lowerBound == 0.9
    then grade
    else oneHigherLowerBound - deltaAmount

gradeAmountRow : String -> String -> Float -> Html Msg
gradeAmountRow grade letterGrade lowerBound =
  let
    fgrade = String.toFloat grade
    lowerGrade = fgrade * lowerBound
    upperGrade = calculateUpperGrade fgrade lowerBound 0
  in
    tr []
      [ td [] [ text letterGrade ]
      , td [] [ text (toString upperGrade) ]
      , td [] [ text (toString lowerGrade)]
      ]

gradeTable : Model -> Html Msg
gradeTable model =
  if parseableNumber model.grade
    then table []
          [ gradeAmountRow model.grade "A" 0.9 ]
    else span [] []

view : Model -> Html Msg
view model =
  div []
    [
      h2 [] [ text "Grades" ],
      div [] [ text ("Grade is " ++ model.grade) ],
      input [ type' "text", value model.grade, onInput UpdateGrade ] [],
      gradeTable model
    ]
