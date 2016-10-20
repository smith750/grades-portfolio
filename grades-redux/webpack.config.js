var webpack = require('webpack');
module.exports = {
    entry: {
        app: "./src/app.jsx",
    },
    output: {
        path: __dirname + '/build',
        filename: "[name].bundle.js"
    },
    resolve: {
        modulesDirectories: ['node_modules', 'lib'],
        extensions: ['', '.js']
    },
    module: {
        preLoaders: [
            {test: /\.json$/, loader: 'json'}
        ],
        loaders: [
            {test: /\.jsx$/, loaders: ['babel', 'babel-loader'], exclude: /node_modules/},
            {test: /\.js$/, loaders: ['babel', 'babel-loader'], exclude: /node_modules/}
        ]
    },
    plugins: [
        new webpack.NoErrorsPlugin()
    ]

}
