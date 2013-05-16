/*
 * Copyright 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.openfin',
    dependencies: [],
    obj: function () {
        return {
            init: (function () {
                $(document).ready(function () {
                    var container = $('.OG-Openfin'), url_bar = $('.og-url', container).val(window.location.href),
                        forward = $('.og-forward', container), back = $('.og-back');
                    if (!container) return;
                    forward.click(function (event) {
                        window.history.forward();
                        url_bar.val(window.location.href);
                    });
                    back.click(function () {
                        window.history.back();
                        url_bar.val(window.location.href);
                    });
                    container.find('form').submit(function (event) { return false; });
                    $('a[href]').live('click', function (e) { url_bar.val($(this)[0].href); });
                    $(window).on('hashchange', function () {
                        url_bar.val(window.location.href);
                    });
                });
            })()
        };
    }
});