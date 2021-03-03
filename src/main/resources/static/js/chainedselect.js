jQuery.fn.chained = function(parent_selector, options) {
    return this.each(function() {
        var self = this;
        var backup = jQuery(self).clone();
        jQuery(parent_selector).each(function() {
            jQuery(this).bind("change", function() {
                jQuery(self).html(backup.html());
                var selected = "";
                jQuery(parent_selector).each(function() { selected += "\\" + jQuery(":selected", this).val(); });
                selected = selected.substr(1);
                var first = jQuery(parent_selector).first();
                var selected_first = jQuery(":selected", first).val();
                jQuery("option", self).each(function() {
                    if (!jQuery(this).hasClass(selected) &&
                        !jQuery(this).hasClass(selected_first) && jQuery(this).val() !== "") { jQuery(this).remove(); }
                });
                if (1 == jQuery("option", self).size() && jQuery(self).val() === "") { jQuery(self).attr("disabled", "disabled"); }
                else { jQuery(self).removeAttr("disabled"); }
                jQuery(self).trigger("change");
            });
            if ( !jQuery("option:selected", this).length ) { jQuery("option", this).first().attr("selected", "selected");}
            jQuery(this).trigger("change");
        }); }); };
jQuery.fn.chainedTo = jQuery.fn.chained;
jQuery(document).ready(function(){ jQuery("#category_select").chained("#type_select"); });