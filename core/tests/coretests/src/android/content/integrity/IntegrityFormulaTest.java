/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.content.integrity;

import static android.content.integrity.IntegrityFormula.COMPOUND_FORMULA_TAG;

import static com.google.common.truth.Truth.assertThat;

import static org.testng.Assert.assertThrows;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IntegrityFormulaTest {

    @Test
    public void createEqualsFormula_packageName() {
        String packageName = "com.test.app";
        IntegrityFormula formula =
                IntegrityFormula.PACKAGE_NAME.equalTo(packageName);

        AtomicFormula.StringAtomicFormula stringAtomicFormula =
                (AtomicFormula.StringAtomicFormula) formula;

        assertThat(stringAtomicFormula.getKey()).isEqualTo(AtomicFormula.PACKAGE_NAME);
        assertThat(stringAtomicFormula.getValue()).isEqualTo(packageName);
        assertThat(stringAtomicFormula.getIsHashedValue()).isEqualTo(false);
    }

    @Test
    public void createEqualsFormula_appCertificate() {
        String appCertificate = "com.test.app";
        IntegrityFormula formula =
                IntegrityFormula.APP_CERTIFICATE.equalTo(appCertificate);

        AtomicFormula.StringAtomicFormula stringAtomicFormula =
                (AtomicFormula.StringAtomicFormula) formula;

        assertThat(stringAtomicFormula.getKey()).isEqualTo(AtomicFormula.APP_CERTIFICATE);
        assertThat(stringAtomicFormula.getValue()).doesNotMatch(appCertificate);
        assertThat(stringAtomicFormula.getIsHashedValue()).isEqualTo(true);
    }

    @Test
    public void createEqualsFormula_installerName() {
        String installerName = "com.test.app";
        IntegrityFormula formula =
                IntegrityFormula.INSTALLER_NAME.equalTo(installerName);

        AtomicFormula.StringAtomicFormula stringAtomicFormula =
                (AtomicFormula.StringAtomicFormula) formula;

        assertThat(stringAtomicFormula.getKey()).isEqualTo(AtomicFormula.INSTALLER_NAME);
        assertThat(stringAtomicFormula.getValue()).isEqualTo(installerName);
        assertThat(stringAtomicFormula.getIsHashedValue()).isEqualTo(false);
    }

    @Test
    public void createEqualsFormula_installerCertificate() {
        String installerCertificate = "com.test.app";
        IntegrityFormula formula =
                IntegrityFormula.INSTALLER_CERTIFICATE.equalTo(installerCertificate);

        AtomicFormula.StringAtomicFormula stringAtomicFormula =
                (AtomicFormula.StringAtomicFormula) formula;

        assertThat(stringAtomicFormula.getKey()).isEqualTo(AtomicFormula.INSTALLER_CERTIFICATE);
        assertThat(stringAtomicFormula.getValue()).doesNotMatch(installerCertificate);
        assertThat(stringAtomicFormula.getIsHashedValue()).isEqualTo(true);
    }

    @Test
    public void createEqualsFormula_versionCode() {
        int versionCode = 12;
        IntegrityFormula formula =
                IntegrityFormula.VERSION_CODE.equalTo(versionCode);

        AtomicFormula.LongAtomicFormula stringAtomicFormula =
                (AtomicFormula.LongAtomicFormula) formula;

        assertThat(stringAtomicFormula.getKey()).isEqualTo(AtomicFormula.VERSION_CODE);
        assertThat(stringAtomicFormula.getValue()).isEqualTo(versionCode);
        assertThat(stringAtomicFormula.getOperator()).isEqualTo(AtomicFormula.EQ);
    }

    @Test
    public void createEqualsFormula_invalidKeyTypeForStringParameter() {
        assertThrows(
                IllegalArgumentException.class,
                () -> IntegrityFormula.PRE_INSTALLED.equalTo("wrongString"));
    }

    @Test
    public void createEqualsFormula_invalidKeyTypeForLongParameter() {
        assertThrows(
                IllegalArgumentException.class,
                () -> IntegrityFormula.PACKAGE_NAME.equalTo(12));
    }

    @Test
    public void createGreaterThanFormula_versionCode() {
        int versionCode = 12;
        IntegrityFormula formula =
                IntegrityFormula.VERSION_CODE.greaterThan(versionCode);

        AtomicFormula.LongAtomicFormula stringAtomicFormula =
                (AtomicFormula.LongAtomicFormula) formula;

        assertThat(stringAtomicFormula.getKey()).isEqualTo(AtomicFormula.VERSION_CODE);
        assertThat(stringAtomicFormula.getValue()).isEqualTo(versionCode);
        assertThat(stringAtomicFormula.getOperator()).isEqualTo(AtomicFormula.GT);
    }

    @Test
    public void createGreaterThanFormula_invalidKeyTypeForLongParameter() {
        assertThrows(
                IllegalArgumentException.class,
                () -> IntegrityFormula.PACKAGE_NAME.greaterThan(12));
    }

    @Test
    public void createGreaterThanOrEqualsToFormula_versionCode() {
        int versionCode = 12;
        IntegrityFormula formula =
                IntegrityFormula.VERSION_CODE.greaterThanOrEquals(versionCode);

        AtomicFormula.LongAtomicFormula stringAtomicFormula =
                (AtomicFormula.LongAtomicFormula) formula;

        assertThat(stringAtomicFormula.getKey()).isEqualTo(AtomicFormula.VERSION_CODE);
        assertThat(stringAtomicFormula.getValue()).isEqualTo(versionCode);
        assertThat(stringAtomicFormula.getOperator()).isEqualTo(AtomicFormula.GTE);
    }

    @Test
    public void createGreaterThanOrEqualsToFormula_invalidKeyTypeForLongParameter() {
        assertThrows(
                IllegalArgumentException.class,
                () -> IntegrityFormula.PACKAGE_NAME.greaterThanOrEquals(12));
    }

    @Test
    public void createIsTrueFormula_preInstalled() {
        IntegrityFormula formula = IntegrityFormula.PRE_INSTALLED.equalTo(true);

        AtomicFormula.BooleanAtomicFormula stringAtomicFormula =
                (AtomicFormula.BooleanAtomicFormula) formula;

        assertThat(stringAtomicFormula.getKey()).isEqualTo(AtomicFormula.PRE_INSTALLED);
        assertThat(stringAtomicFormula.getValue()).isTrue();
    }

    @Test
    public void createIsTrueFormula_invalidKeyTypeForBoolParameter() {
        assertThrows(
                IllegalArgumentException.class,
                () -> IntegrityFormula.PACKAGE_NAME.equalTo(true));
    }

    @Test
    public void createAllFormula() {
        String packageName = "com.test.package";
        String certificateName = "certificate";
        IntegrityFormula formula1 =
                IntegrityFormula.PACKAGE_NAME.equalTo(packageName);
        IntegrityFormula formula2 =
                IntegrityFormula.APP_CERTIFICATE.equalTo(certificateName);

        IntegrityFormula compoundFormula = IntegrityFormula.all(formula1, formula2);

        assertThat(compoundFormula.getTag()).isEqualTo(COMPOUND_FORMULA_TAG);
    }

    @Test
    public void createAnyFormula() {
        String packageName = "com.test.package";
        String certificateName = "certificate";
        IntegrityFormula formula1 =
                IntegrityFormula.PACKAGE_NAME.equalTo(packageName);
        IntegrityFormula formula2 =
                IntegrityFormula.APP_CERTIFICATE.equalTo(certificateName);

        IntegrityFormula compoundFormula = IntegrityFormula.any(formula1, formula2);

        assertThat(compoundFormula.getTag()).isEqualTo(COMPOUND_FORMULA_TAG);
    }

    @Test
    public void createNotFormula() {
        String packageName = "com.test.package";

        IntegrityFormula compoundFormula =
                IntegrityFormula.not(
                        IntegrityFormula.PACKAGE_NAME.equalTo(packageName));

        assertThat(compoundFormula.getTag()).isEqualTo(COMPOUND_FORMULA_TAG);
    }
}
