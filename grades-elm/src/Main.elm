module Main exposing (..)

import Html exposing (..)
import Html.App as Html
import Html.Attributes exposing (..)

main : Program Never
main =
    Html.program
        { init = init
        , view = view
        , update = update
        , subscriptions = subscriptions
        }
