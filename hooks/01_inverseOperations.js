module.exports = {
    'generate:before': generator => {
        if (generator.templateParams && generator.templateParams['inverseOperations'] === 'true') {
            console.log("Operations are inverted!");
            const asyncapi = generator.asyncapi;
            for (let [key, value] of Object.entries(asyncapi.channels())) {
                let publish = value._json.publish;
                value._json.publish = value._json.subscribe;
                value._json.subscribe = publish;
                if (!value._json.subscribe) {
                    delete value._json.subscribe;
                }
                if (!value._json.publish) {
                    delete value._json.publish;
                }
            }
        }
    }
};