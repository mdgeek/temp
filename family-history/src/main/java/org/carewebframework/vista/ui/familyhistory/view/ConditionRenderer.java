/**
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 *
 * This Source Code Form is also subject to the terms of the Health-Related Additional
 * Disclaimer of Warranty and Limitation of Liability available at
 * http://www.carewebframework.org/licensing/disclaimer.
 */
package org.carewebframework.vista.ui.familyhistory.view;

import ca.uhn.fhir.model.dstu2.composite.AgeDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.FamilyMemberHistory.Condition;
import ca.uhn.fhir.model.primitive.StringDt;

import org.carewebframework.fhir.common.FhirUtil;
import org.carewebframework.ui.zk.AbstractRowRenderer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Row;

/**
 * Renderer for a goal.
 */
public class ConditionRenderer extends AbstractRowRenderer<Condition, Object> {
    
    public ConditionRenderer() {
        super(null, null);
    }
    
    /**
     * Render the row for the specified family member condition.
     *
     * @param row Row to render.
     * @param condition A family member condition.
     */
    @Override
    public Component renderRow(Row row, Condition condition) {
        createCell(row, condition.getNote()); // provider narrative
        AgeDt age = FhirUtil.getTyped(condition.getOnset(), AgeDt.class);
        StringDt ageStr = FhirUtil.getTyped(condition.getOnset(), StringDt.class);
        createCell(row, age != null ? age.getValue() : ageStr != null ? ageStr.getValue() : null); // age at diagnosis
        createCell(row, ""); // date modified
        CodingDt coding = FhirUtil.getCoding(condition.getType().getCoding(), "http://hl7.org/fhir/sid/icd-9");
        createCell(row, coding == null ? null : coding.getCode()); // icd
        row.setSclass("alert-warning");
        return null;
    }
}