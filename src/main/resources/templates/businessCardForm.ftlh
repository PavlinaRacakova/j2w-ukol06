[#ftl]
[@c.page]
    <div class="detail">
        [@form.form method="post" class="businessCard" bind="businessCard" title=title]
            [@form.input name="name" label="Full name" maxlength="100" required="required" /]
            [@form.input name="company" label="Company" maxlength="100" required="required" /]
            [@form.input name="street" label="Street & house number" maxlength="100" required="required" /]
            [@form.input name="town" label="Town" maxlength="100" required="required" /]
            [@form.input name="zipCode" label="Zip code" minlength="5" maxlength="5" pattern="[0-9]{5}" required="required" /]
            [@form.input name="email" label="E-mail *" maxlength="100" type="email" /]
            [@form.input name="phoneNumber" label="Phone number *" maxlength="100" type="tel" /]
            [@form.input name="web" label="Web *" maxlength="100" type="url" /]
            <div class="form-controls">
                <button type="submit">Submit</button>
            </div>
        [/@form.form]
        [@c.back /]
    </div>
[/@c.page]
